/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lombok.javac.handlers;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import griffon.transform.ResourcesAware;
import lombok.ast.TypeRef;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.ast.JavacType;
import org.codehaus.griffon.runtime.core.ResourceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;

import static com.sun.tools.javac.code.Flags.FINAL;
import static com.sun.tools.javac.code.Flags.PRIVATE;
import static lombok.ast.AST.*;
import static lombok.javac.handlers.AstBuilder.defVar;
import static lombok.javac.handlers.HandlerUtils.NIL_EXPRESSION;
import static lombok.javac.handlers.HandlerUtils.injectMethod;
import static lombok.javac.handlers.JavacHandlerUtil.*;

/**
 * @author Andres Almiray
 */
public class HandleResourcesAware extends JavacAnnotationHandler<ResourcesAware> {
    private static final Logger LOG = LoggerFactory.getLogger(HandleResourcesAware.class);
    private static final String FIELD_NAME = "this$resourceLocator";
    private static final String NAME_PARAM = "name";
    private static final String METHOD_GET_RESOURCE_AS_URL = "getResourceAsURL";
    private static final String METHOD_GET_RESOURCE_AS_STREAM = "getResourceAsStream";
    private static final String METHOD_GET_RESOURCES = "getResources";

    public void handle(AnnotationValues<ResourcesAware> annotation, JCTree.JCAnnotation ast, JavacNode annotationNode) {
        deleteAnnotationIfNeccessary(annotationNode, ResourcesAware.class);

        JavacNode typeNode = annotationNode.up();
        switch (typeNode.getKind()) {
            case TYPE:
                if ((((JCTree.JCClassDecl) typeNode.get()).mods.flags & Flags.INTERFACE) != 0) {
                    annotationNode.addError("@ResourcesAware is legal only on classes and enums.");
                    return;
                }

                if (fieldExists(FIELD_NAME, typeNode) != MemberExistsResult.NOT_EXISTS) {
                    annotationNode.addWarning("Field '" + FIELD_NAME + "' already exists.");
                    return;
                }

                final JavacType type = JavacType.typeOf(annotationNode, ast);
                addResourceLocatorSupport(type);
                typeNode.rebuild();
                return;
            default:
                annotationNode.addError("@ResourcesAware is legal only on types.");
                return;
        }
    }

    private void addResourceLocatorSupport(JavacType type) {
        injectResourceHandlerInterface(type.node());
        createResourceLocatorField(type.node());
        injectResourceHandlingMethodMethod(type, METHOD_GET_RESOURCE_AS_URL, URL.class);
        injectResourceHandlingMethodMethod(type, METHOD_GET_RESOURCE_AS_STREAM, InputStream.class);
        injectResourceHandlingMethodMethod(type, METHOD_GET_RESOURCES, java.util.List.class, URL.class.getName());

        if (LOG.isDebugEnabled()) LOG.debug("Modified " + type.node().getName() + " as a ResourceHandler.");
    }

    private void injectResourceHandlerInterface(JavacNode typeNode) {
        HandlerUtils.TokenBuilder b = new HandlerUtils.TokenBuilder(typeNode);
        b.addInterface("griffon.core.ResourceHandler", typeNode);
    }

    private void injectResourceHandlingMethodMethod(JavacType type, String methodName, Class returnType) {
        injectResourceHandlingMethodMethod(type, methodName, returnType, null);
    }

    private void injectResourceHandlingMethodMethod(JavacType type, String methodName, Class returnType, String typeParam) {
        TypeRef returnTypeRef = Type(returnType);
        if (typeParam != null) {
            returnTypeRef.withTypeArgument(Type(typeParam));
        }

        injectMethod(type,
            MethodDecl(returnTypeRef, methodName)
                .makePublic()
                .withArgument(Arg(Type(String.class), NAME_PARAM))
                .withStatement(Return(Call(Name(FIELD_NAME), methodName)
                    .withArgument(Name(NAME_PARAM))))
        );
    }

    private void createResourceLocatorField(JavacNode typeNode) {
        TreeMaker maker = typeNode.getTreeMaker();

        JCTree.JCExpression type = chainDotsString(typeNode, ResourceLocator.class.getName());
        JCTree.JCExpression instance = maker.NewClass(null, NIL_EXPRESSION, type, NIL_EXPRESSION, null);

        injectField(typeNode, defVar(FIELD_NAME)
            .modifiers(PRIVATE | FINAL)
            .type(ResourceLocator.class)
            .withValue(instance)
            .$(typeNode));

    }
}

/*
 * Copyright 2009-2012 the original author or authors.
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

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import griffon.core.MVCClosure;
import griffon.core.MVCGroup;
import griffon.transform.MVCAware;
import groovy.lang.Closure;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import java.util.Map;

import static lombok.javac.handlers.AstBuilder.defMethod;
import static lombok.javac.handlers.AstBuilder.defVar;
import static lombok.javac.handlers.HandlerUtils.extractArgNames;
import static lombok.javac.handlers.JavacHandlerUtil.deleteAnnotationIfNeccessary;
import static lombok.javac.handlers.JavacHandlerUtil.injectMethod;

/**
 * @author Andres Almiray
 */
public class HandleMVCAware extends JavacAnnotationHandler<MVCAware> {
    // private static final Logger LOG = LoggerFactory.getLogger(HandleMVCAware.class);

    private static final String CREATE_MVC_GROUP = "createMVCGroup";
    private static final String DESTROY_MVC_GROUP = "destroyMVCGroup";
    private static final String BUILD_MVC_GROUP = "buildMVCGroup";
    private static final String WITH_MVC_GROUP = "withMVCGroup";
    private static final String MVC_TYPE = "mvcType";
    private static final String MVC_NAME = "mvcName";
    private static final String HANDLER = "handler";
    private static final String ARGS = "args";

    public void handle(AnnotationValues<MVCAware> annotation, JCTree.JCAnnotation ast, JavacNode annotationNode) {
        deleteAnnotationIfNeccessary(annotationNode, MVCAware.class);

        JavacNode typeNode = annotationNode.up();
        switch (typeNode.getKind()) {
            case TYPE:
                addMessageSourceAwareSupport(typeNode);
                return;
            default:
                annotationNode.addError("@MVCAware is legal only on types.");
                return;
        }
    }

    private void addMessageSourceAwareSupport(JavacNode typeNode) {
        HandlerUtils.TokenBuilder b = new HandlerUtils.TokenBuilder(typeNode);
        b.addInterface("griffon.core.MVCHandler", typeNode);

        TreeMaker m = typeNode.getTreeMaker();

        List<JCTree.JCVariableDecl> params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode));
        List<JCTree.JCExpression> args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(BUILD_MVC_GROUP)
            .returning(MVCGroup.class)
            .withParams(params)
            .withBody(body(BUILD_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(BUILD_MVC_GROUP)
            .returning(MVCGroup.class)
            .withParams(params)
            .withBody(body(BUILD_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(BUILD_MVC_GROUP)
            .returning(MVCGroup.class)
            .withParams(params)
            .withBody(body(BUILD_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(BUILD_MVC_GROUP)
            .returning(MVCGroup.class)
            .withParams(params)
            .withBody(body(BUILD_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(BUILD_MVC_GROUP)
            .returning(MVCGroup.class)
            .withParams(params)
            .withBody(body(BUILD_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(BUILD_MVC_GROUP)
            .returning(MVCGroup.class)
            .withParams(params)
            .withBody(body(BUILD_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(CREATE_MVC_GROUP)
            .returning(java.util.List.class)
            .withParams(params)
            .withBody(body(CREATE_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(CREATE_MVC_GROUP)
            .returning(java.util.List.class)
            .withParams(params)
            .withBody(body(CREATE_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(CREATE_MVC_GROUP)
            .returning(java.util.List.class)
            .withParams(params)
            .withBody(body(CREATE_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(CREATE_MVC_GROUP)
            .returning(java.util.List.class)
            .withParams(params)
            .withBody(body(CREATE_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(CREATE_MVC_GROUP)
            .returning(java.util.List.class)
            .withParams(params)
            .withBody(body(CREATE_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(CREATE_MVC_GROUP)
            .returning(java.util.List.class)
            .withParams(params)
            .withBody(body(CREATE_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(DESTROY_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(DESTROY_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(Closure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(Closure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(Closure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(Closure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(Closure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(Closure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(MVCClosure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(MVCClosure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(MVCClosure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(MVCClosure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(MVCClosure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        params = List.of(
            defVar(ARGS)
                .type(Map.class)
                .$(typeNode),
            defVar(MVC_TYPE)
                .type(String.class)
                .$(typeNode),
            defVar(MVC_NAME)
                .type(String.class)
                .$(typeNode),
            defVar(HANDLER)
                .type(MVCClosure.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(WITH_MVC_GROUP)
            .withParams(params)
            .withBody(bodyWithoutReturn(WITH_MVC_GROUP, args, b))
            .$(typeNode));

        // if (LOG.isDebugEnabled()) LOG.debug("Modified " + typeNode.getName() + " as MVCHandler.");
    }

    private List<JCTree.JCStatement> body(String methodName, List<JCTree.JCExpression> args, HandlerUtils.TokenBuilder b) {
        return List.<JCTree.JCStatement>of(b.getTreeMaker().Return(invokeMVCMethod(methodName, args, b)));
    }

    private List<JCTree.JCStatement> bodyWithoutReturn(String methodName, List<JCTree.JCExpression> args, HandlerUtils.TokenBuilder b) {
        return List.<JCTree.JCStatement>of(b.getTreeMaker().Exec(invokeMVCMethod(methodName, args, b)));
    }

    private JCTree.JCExpression getApplication(HandlerUtils.TokenBuilder b) {
        return b.staticCallExpr("griffon.util.ApplicationHolder", "getApplication");
    }

    private JCTree.JCExpression getMVCGroupManager(HandlerUtils.TokenBuilder b) {
        return b.invoke(getApplication(b), "getMvcGroupManager");
    }

    private JCTree.JCExpression invokeMVCMethod(String methodName, List<JCTree.JCExpression> args, HandlerUtils.TokenBuilder b) {
        return b.invoke(getMVCGroupManager(b), methodName, args);
    }
}

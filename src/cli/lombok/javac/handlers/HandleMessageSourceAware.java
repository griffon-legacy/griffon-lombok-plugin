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
import griffon.core.i18n.NoSuchMessageException;
import griffon.transform.MessageSourceAware;
import lombok.core.AnnotationValues;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

import static lombok.javac.handlers.AstBuilder.defMethod;
import static lombok.javac.handlers.AstBuilder.defVar;
import static lombok.javac.handlers.HandlerUtils.extractArgNames;
import static lombok.javac.handlers.JavacHandlerUtil.deleteAnnotationIfNeccessary;
import static lombok.javac.handlers.JavacHandlerUtil.injectMethod;

/**
 * @author Andres Almiray
 */
public class HandleMessageSourceAware extends JavacAnnotationHandler<MessageSourceAware> {
    private static final Logger LOG = LoggerFactory.getLogger(HandleMessageSourceAware.class);

    private static final String KEY = "key";
    private static final String LOCALE = "locale";
    private static final String ARGS = "args";
    private static final String DEFAULT_MESSAGE = "defaultMessage";
    private static final String METHOD_GET_MESSAGE = "getMessage";

    public void handle(AnnotationValues<MessageSourceAware> annotation, JCTree.JCAnnotation ast, JavacNode annotationNode) {
        deleteAnnotationIfNeccessary(annotationNode, MessageSourceAware.class);

        JavacNode typeNode = annotationNode.up();
        switch (typeNode.getKind()) {
            case TYPE:
                addMessageSourceAwareSupport(typeNode);
                return;
            default:
                annotationNode.addError("@MessageSourceAware is legal only on types.");
                return;
        }
    }

    private void addMessageSourceAwareSupport(JavacNode typeNode) {
        HandlerUtils.TokenBuilder b = new HandlerUtils.TokenBuilder(typeNode);
        b.addInterface("griffon.core.i18n.MessageSource", typeNode);

        TreeMaker m = typeNode.getTreeMaker();

        List<JCTree.JCVariableDecl> params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode));
        List<JCTree.JCExpression> args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .throwing(NoSuchMessageException.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(LOCALE)
                .type(Locale.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .throwing(NoSuchMessageException.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Object[].class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .throwing(NoSuchMessageException.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Object[].class)
                .$(typeNode),
            defVar(LOCALE)
                .type(Locale.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .throwing(NoSuchMessageException.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(java.util.List.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .throwing(NoSuchMessageException.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(java.util.List.class)
                .$(typeNode),
            defVar(LOCALE)
                .type(Locale.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .throwing(NoSuchMessageException.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(java.util.Map.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .throwing(NoSuchMessageException.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(java.util.Map.class)
                .$(typeNode),
            defVar(LOCALE)
                .type(Locale.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .throwing(NoSuchMessageException.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(DEFAULT_MESSAGE)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(DEFAULT_MESSAGE)
                .type(String.class)
                .$(typeNode),
            defVar(LOCALE)
                .type(Locale.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Object[].class)
                .$(typeNode),
            defVar(DEFAULT_MESSAGE)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(Object[].class)
                .$(typeNode),
            defVar(DEFAULT_MESSAGE)
                .type(String.class)
                .$(typeNode),
            defVar(LOCALE)
                .type(Locale.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(java.util.List.class)
                .$(typeNode),
            defVar(DEFAULT_MESSAGE)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(java.util.List.class)
                .$(typeNode),
            defVar(DEFAULT_MESSAGE)
                .type(String.class)
                .$(typeNode),
            defVar(LOCALE)
                .type(Locale.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(java.util.Map.class)
                .$(typeNode),
            defVar(DEFAULT_MESSAGE)
                .type(String.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        params = List.of(
            defVar(KEY)
                .type(String.class)
                .$(typeNode),
            defVar(ARGS)
                .type(java.util.Map.class)
                .$(typeNode),
            defVar(DEFAULT_MESSAGE)
                .type(String.class)
                .$(typeNode),
            defVar(LOCALE)
                .type(Locale.class)
                .$(typeNode));
        args = extractArgNames(params, m);
        injectMethod(typeNode, defMethod(METHOD_GET_MESSAGE)
            .returning(String.class)
            .withParams(params)
            .withBody(body(args, b))
            .$(typeNode));

        if (LOG.isDebugEnabled()) LOG.debug("Modified " + typeNode.getName() + " as MessageSource.");
    }

    private List<JCTree.JCStatement> body(List<JCTree.JCExpression> args, HandlerUtils.TokenBuilder b) {
        return List.<JCTree.JCStatement>of(b.getTreeMaker().Return(invokeGetMessage(args, b)));
    }

    private JCTree.JCExpression getApplication(HandlerUtils.TokenBuilder b) {
        return b.staticCallExpr("griffon.util.ApplicationHolder", "getApplication");
    }

    private JCTree.JCExpression invokeGetMessage(List<JCTree.JCExpression> args, HandlerUtils.TokenBuilder b) {
        return b.invoke(getApplication(b), METHOD_GET_MESSAGE, args);
    }
}

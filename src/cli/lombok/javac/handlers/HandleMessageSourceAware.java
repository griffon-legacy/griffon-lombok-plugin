/*
 * Copyright 2012-2013 the original author or authors.
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
import griffon.transform.MessageSourceAware;
import lombok.core.AnnotationValues;
import lombok.core.MessageSourceAwareConstants;
import lombok.core.handlers.MessageSourceAwareHandler;
import lombok.javac.JavacAnnotationHandler;
import lombok.javac.JavacNode;
import lombok.javac.handlers.ast.JavacType;

import static lombok.core.util.ErrorMessages.canBeUsedOnClassAndEnumOnly;
import static lombok.javac.handlers.JavacHandlerUtil.deleteAnnotationIfNeccessary;

/**
 * @author Andres Almiray
 */
public class HandleMessageSourceAware extends JavacAnnotationHandler<MessageSourceAware> {
    private final JavacMessageSourceAwareHandler handler = new JavacMessageSourceAwareHandler();

    @Override
    public void handle(AnnotationValues<MessageSourceAware> annotation, final JCTree.JCAnnotation source, final JavacNode annotationNode) {
        deleteAnnotationIfNeccessary(annotationNode, MessageSourceAware.class);

        JavacType type = JavacType.typeOf(annotationNode, source);
        if (type.isAnnotation() || type.isInterface()) {
            annotationNode.addError(canBeUsedOnClassAndEnumOnly(MessageSourceAware.class));
            return;
        }

        JavacUtil.addInterface(type.node(), MessageSourceAwareConstants.MESSAGE_SOURCE_TYPE);
        handler.addMessageSourceSupport(type);
        type.editor().rebuild();
    }

    private static class JavacMessageSourceAwareHandler extends MessageSourceAwareHandler<JavacType> {
    }
}

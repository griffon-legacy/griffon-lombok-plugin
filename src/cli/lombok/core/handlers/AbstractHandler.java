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

package lombok.core.handlers;

import lombok.ast.*;
import lombok.core.BaseConstants;
import lombok.core.util.MethodDescriptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static lombok.ast.AST.*;

/**
 * @author Andres Almiray
 */
public abstract class AbstractHandler<TYPE_TYPE extends IType<? extends IMethod<?, ?, ?, ?>, ?, ?, ?, ?, ?>> implements BaseConstants {
    public TypeRef asTypeRef(MethodDescriptor.Type type) {
        TypeRef typeRef = Type(type.type);
        if (type.parameters.length > 0) {
            List<TypeRef> types = new ArrayList<TypeRef>();
            for (int i = 0; i < type.parameters.length; i++) {
                types.add(Type(type.parameters[i]));
            }
            typeRef.withTypeArguments(types);
        }
        if (type.dimensions > 0) typeRef.withDimensions(type.dimensions);
        return typeRef;
    }

    public Expression<?> getApplicationExpression() {
        return Call(Name("griffon.util.ApplicationHolder"), "getApplication");
    }

    public void addField(final TYPE_TYPE type, String fieldType, String fieldName) {
        addField(type, fieldType, fieldName, null);
    }

    public void addField(final TYPE_TYPE type, String fieldType, String fieldName, Expression<?> initialization) {
        final FieldDecl fieldDecl = FieldDecl(Type(fieldType), fieldName)
            .makePrivate();
        if (initialization != null) fieldDecl.withInitialization(initialization);
        type.editor().injectField(fieldDecl);
    }

    public void addField(final TYPE_TYPE type, MethodDescriptor.Type fieldType, String fieldName) {
        addField(type, fieldType, fieldName, null);
    }

    public void addField(final TYPE_TYPE type, MethodDescriptor.Type fieldType, String fieldName, Expression<?> initialization) {
        final FieldDecl fieldDecl = FieldDecl(asTypeRef(fieldType), fieldName)
            .makePrivate();
        if (initialization != null) fieldDecl.withInitialization(initialization);
        type.editor().injectField(fieldDecl);
    }

    public void delegateMethodsTo(TYPE_TYPE type, MethodDescriptor[] methodDescriptors, Expression<?> receiver) {
        delegateMethodsTo(type, methodDescriptors, receiver, Collections.<String, String>emptyMap());
    }

    public void delegateMethodsTo(TYPE_TYPE type, MethodDescriptor[] methodDescriptors, Expression<?> receiver, Map<String, String> methodMapper) {
        for (MethodDescriptor methodDesc : methodDescriptors) {
            List<Argument> methodArgs = new ArrayList<Argument>();
            List<Expression<?>> callArgs = new ArrayList<Expression<?>>();
            int argCounter = 0;
            for (MethodDescriptor.Type arg : methodDesc.arguments) {
                String argName = "arg" + argCounter++;
                methodArgs.add(Arg(asTypeRef(arg), argName));
                callArgs.add(Name(argName));
            }

            String delegateMethodName = methodMapper.get(methodDesc.methodName);
            if (delegateMethodName == null) delegateMethodName = methodDesc.methodName;

            Call methodCall = Call(receiver, delegateMethodName)
                .withArguments(callArgs);
            MethodDecl methodDecl = MethodDecl(asTypeRef(methodDesc.returnType), methodDesc.methodName)
                .makePublic()
                .withArguments(methodArgs);
            if (VOID.equals(methodDesc.returnType.type)) {
                methodDecl.withStatement(methodCall);
            } else {
                methodDecl.withStatement(Return(methodCall));
            }
            if (methodDesc.typeParameters.length > 0) {
                List<TypeParam> types = new ArrayList<TypeParam>();
                for (int i = 0; i < methodDesc.typeParameters.length; i++) {
                    types.add(TypeParam(methodDesc.typeParameters[i]));
                }
                methodDecl.withTypeParameters(types);
            }
            if (methodDesc.exceptions.length > 0) {
                List<TypeRef> exceptions = new ArrayList<TypeRef>();
                for (int i = 0; i < methodDesc.exceptions.length; i++) {
                    exceptions.add(asTypeRef(methodDesc.exceptions[i]));
                }
                methodDecl.withThrownExceptions(exceptions);
            }

            type.editor().injectMethod(methodDecl);
        }
    }
}

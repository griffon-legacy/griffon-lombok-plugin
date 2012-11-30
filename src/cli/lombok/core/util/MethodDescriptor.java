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

package lombok.core.util;

/**
 * @author Andres Almiray
 */
public class MethodDescriptor {
    private static final String[] EMPTY_PARAMETERS = new String[0];
    private static final Type[] EMPTY_TYPES = new Type[0];

    public static class Type {
        public final String type;
        public final String[] parameters;
        public final int dimensions;
        public final String typeSignature;

        public Type(String type) {
            this(type, 0, EMPTY_PARAMETERS);
        }

        public Type(String type, int dimensions) {
            this(type, dimensions, EMPTY_PARAMETERS);
        }

        public Type(String type, String[] parameters) {
            this(type, 0, parameters);
        }

        public Type(String type, int dimensions, String[] parameters) {
            this.type = type;
            this.dimensions = dimensions;
            this.parameters = parameters != null ? parameters : EMPTY_PARAMETERS;
            this.typeSignature = createTypeSignature();
        }

        private String createTypeSignature() {
            StringBuilder b = new StringBuilder(type);
            if (parameters.length > 0) {
                b.append("<");
                for (int i = 0; i < parameters.length; i++) {
                    b.append(parameters[i]);
                    if (i < parameters.length - 1) b.append(", ");
                }
                b.append(">");
            }
            for (int d = 0; d < dimensions; d++) {
                b.append("[]");
            }
            return b.toString();
        }
    }

    public final String methodName;
    public final Type returnType;
    public final String[] typeParameters;
    public final Type[] exceptions;
    public final Type[] arguments;
    public final String signature;

    public MethodDescriptor(Type returnType, String[] typeParameters, String methodName, Type[] arguments, Type[] exceptions) {
        this.returnType = returnType;
        this.methodName = methodName;
        this.typeParameters = typeParameters != null ? typeParameters : EMPTY_PARAMETERS;
        this.arguments = arguments != null ? arguments : EMPTY_TYPES;
        this.exceptions = exceptions != null ? exceptions : EMPTY_TYPES;
        this.signature = createMethodSignature();
    }

    private String createMethodSignature() {
        StringBuilder b = new StringBuilder();
        if (typeParameters.length > 0) {
            b.append("<");
            for (int i = 0; i < typeParameters.length; i++) {
                b.append(typeParameters[i]);
                if (i < typeParameters.length - 1) b.append(", ");
            }
            b.append("> ");
        }
        b.append(returnType.typeSignature)
            .append(" ")
            .append(methodName)
            .append("(");
        if (arguments.length > 0) {
            for (int i = 0; i < arguments.length; i++) {
                b.append(arguments[i].typeSignature)
                    .append(" arg")
                    .append(i);
                if (i < arguments.length - 1) b.append(", ");
            }
        }
        b.append(")");
        if (exceptions.length > 0) {
            b.append(" throws ");
            for (int i = 0; i < exceptions.length; i++) {
                b.append(exceptions[i].typeSignature);
                if (i < exceptions.length - 1) b.append(", ");
            }
        }
        return b.toString();
    }

    public static Type type(String type, String... typeParameters) {
        return new Type(type, typeParameters);
    }

    public static Type type(String type, int dimensions, String... typeParameters) {
        return new Type(type, dimensions, typeParameters);
    }

    public static String[] typeParams(String... typeParameters) {
        return typeParameters;
    }

    public static Type[] args(Type... types) {
        return types;
    }

    public static Type[] throwing(Type... types) {
        return types;
    }

    public static MethodDescriptor method(Type type, String[] typeParameters, String methodName, Type[] args) {
        return new MethodDescriptor(type, typeParameters, methodName, args, EMPTY_TYPES);
    }

    public static MethodDescriptor method(Type type, String[] typeParameters, String methodName) {
        return new MethodDescriptor(type, typeParameters, methodName, EMPTY_TYPES, EMPTY_TYPES);
    }

    public static MethodDescriptor method(Type type, String methodName, Type[] args) {
        return new MethodDescriptor(type, EMPTY_PARAMETERS, methodName, args, EMPTY_TYPES);
    }

    public static MethodDescriptor method(Type type, String methodName) {
        return new MethodDescriptor(type, EMPTY_PARAMETERS, methodName, EMPTY_TYPES, EMPTY_TYPES);
    }

    public static MethodDescriptor method(Type type, String[] typeParameters, String methodName, Type[] args, Type[] exceptions) {
        return new MethodDescriptor(type, typeParameters, methodName, args, exceptions);
    }

    public static MethodDescriptor method(Type type, String methodName, Type[] args, Type[] exceptions) {
        return new MethodDescriptor(type, EMPTY_PARAMETERS, methodName, args, exceptions);
    }
}

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

package lombok.core;

import lombok.core.util.MethodDescriptor;

import static lombok.core.util.MethodDescriptor.*;

/**
 * @author Andres Almiray
 */
public interface MVCAwareConstants extends BaseConstants {
    String MVC_CLOSURE_TYPE = "griffon.core.MVCClosure";
    String GRIFFON_MODEL_TYPE = "griffon.core.GriffonModel";
    String GRIFFON_VIEW_TYPE = "griffon.core.GriffonView";
    String GRIFFON_CONTROLLER_TYPE = "griffon.core.GriffonController";
    String MVC_GROUP = "griffon.core.MVCGroup";
    String MVC_HANDLER_TYPE = "griffon.core.MVCHandler";
    String GRIFFON_MVC_ARTIFACT_TYPE = "griffon.core.GriffonMvcArtifact";

    String METHOD_CREATE_MVC_GROUP = "createMVCGroup";
    String METHOD_BUILD_MVC_GROUP = "buildMVCGroup";
    String METHOD_WITH_MVC_GROUP = "withMVCGroup";
    String METHOD_DESTROY_MVC_GROUP = "destroyMVCGroup";

    String M = "M";
    String V = "V";
    String C = "C";

    MethodDescriptor[] METHODS = new MethodDescriptor[]{
        MethodDescriptor.method(
            type(VOID),
            METHOD_DESTROY_MVC_GROUP,
            args(type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type(MVC_GROUP),
            METHOD_BUILD_MVC_GROUP,
            args(type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type(MVC_GROUP),
            METHOD_BUILD_MVC_GROUP,
            args(type(JAVA_LANG_STRING), type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type(MVC_GROUP),
            METHOD_BUILD_MVC_GROUP,
            args(
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type(MVC_GROUP),
            METHOD_BUILD_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT))
        ),
        MethodDescriptor.method(
            type(MVC_GROUP),
            METHOD_BUILD_MVC_GROUP,
            args(
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type(MVC_GROUP),
            METHOD_BUILD_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT))
        ),

        MethodDescriptor.method(
            typeWithParams(JAVA_UTIL_LIST, wildcard(GRIFFON_MVC_ARTIFACT_TYPE)),
            METHOD_CREATE_MVC_GROUP,
            args(type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            typeWithParams(JAVA_UTIL_LIST, wildcard(GRIFFON_MVC_ARTIFACT_TYPE)),
            METHOD_CREATE_MVC_GROUP,
            args(
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            typeWithParams(JAVA_UTIL_LIST, wildcard(GRIFFON_MVC_ARTIFACT_TYPE)),
            METHOD_CREATE_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT))
        ),
        MethodDescriptor.method(
            typeWithParams(JAVA_UTIL_LIST, wildcard(GRIFFON_MVC_ARTIFACT_TYPE)),
            METHOD_CREATE_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            typeWithParams(JAVA_UTIL_LIST, wildcard(GRIFFON_MVC_ARTIFACT_TYPE)),
            METHOD_CREATE_MVC_GROUP,
            args(
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            typeWithParams(JAVA_UTIL_LIST, wildcard(GRIFFON_MVC_ARTIFACT_TYPE)),
            METHOD_CREATE_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT))
        ),

        MethodDescriptor.method(
            type(VOID),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(GROOVY_LANG_CLOSURE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING),
                type(GROOVY_LANG_CLOSURE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(GROOVY_LANG_CLOSURE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING),
                type(GROOVY_LANG_CLOSURE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(GROOVY_LANG_CLOSURE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(JAVA_LANG_STRING),
                type(GROOVY_LANG_CLOSURE))
        ),

        MethodDescriptor.method(
            type(VOID),
            typeParams(
                typeParam(M, GRIFFON_MODEL_TYPE),
                typeParam(V, GRIFFON_VIEW_TYPE),
                typeParam(C, GRIFFON_CONTROLLER_TYPE)
            ),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(MVC_CLOSURE_TYPE, M, V, C))
        ),
        MethodDescriptor.method(
            type(VOID),
            typeParams(
                typeParam(M, GRIFFON_MODEL_TYPE),
                typeParam(V, GRIFFON_VIEW_TYPE),
                typeParam(C, GRIFFON_CONTROLLER_TYPE)
            ),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING),
                type(MVC_CLOSURE_TYPE, M, V, C))
        ),
        MethodDescriptor.method(
            type(VOID),
            typeParams(
                typeParam(M, GRIFFON_MODEL_TYPE),
                typeParam(V, GRIFFON_VIEW_TYPE),
                typeParam(C, GRIFFON_CONTROLLER_TYPE)
            ),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(MVC_CLOSURE_TYPE, M, V, C))
        ),
        MethodDescriptor.method(
            type(VOID),
            typeParams(
                typeParam(M, GRIFFON_MODEL_TYPE),
                typeParam(V, GRIFFON_VIEW_TYPE),
                typeParam(C, GRIFFON_CONTROLLER_TYPE)
            ),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_STRING),
                type(MVC_CLOSURE_TYPE, M, V, C))
        ),
        MethodDescriptor.method(
            type(VOID),
            typeParams(
                typeParam(M, GRIFFON_MODEL_TYPE),
                typeParam(V, GRIFFON_VIEW_TYPE),
                typeParam(C, GRIFFON_CONTROLLER_TYPE)
            ),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(MVC_CLOSURE_TYPE, M, V, C))
        ),
        MethodDescriptor.method(
            type(VOID),
            typeParams(
                typeParam(M, GRIFFON_MODEL_TYPE),
                typeParam(V, GRIFFON_VIEW_TYPE),
                typeParam(C, GRIFFON_CONTROLLER_TYPE)
            ),
            METHOD_WITH_MVC_GROUP,
            args(
                type(JAVA_UTIL_MAP, JAVA_LANG_STRING, JAVA_LANG_OBJECT),
                type(JAVA_LANG_STRING),
                type(MVC_CLOSURE_TYPE, M, V, C))
        )
    };
}

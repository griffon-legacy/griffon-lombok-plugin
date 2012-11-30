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

import java.util.LinkedHashMap;
import java.util.Map;

import static lombok.core.util.MethodDescriptor.args;
import static lombok.core.util.MethodDescriptor.type;
import static lombok.core.util.MethodDescriptor.typeParams;

/**
 * @author Andres Almiray
 */
public interface ThreadingAwareConstants extends BaseConstants {
    String UITHREAD_MANAGER_TYPE = "griffon.core.UIThreadManager";
    String THREADING_HANDLER_TYPE = "griffon.core.ThreadingHandler";

    String METHOD_IS_UITHREAD = "isUIThread";
    String METHOD_EXEC_INSIDE_UI_ASYNC = "execInsideUIAsync";
    String METHOD_EXEC_INSIDE_UI_SYNC = "execInsideUISync";
    String METHOD_EXEC_OUTSIDE_UI = "execOutsideUI";
    String METHOD_EXEC_FUTURE = "execFuture";
    
    String JAVA_UTIL_CONCURRENT_FUTURE = "java.util.concurrent.Future";
    String JAVA_UTIL_CONCURRENT_CALLABLE = "java.util.concurrent.Callable";
    String JAVA_UTIL_CONCURRENT_EXECUTOR_SERVICE = "java.util.concurrent.ExecutorService";

    Map<String, String> METHOD_MAPPER = new LinkedHashMap<String, String>() {{
        put(METHOD_EXEC_INSIDE_UI_ASYNC, "executeAsync");
        put(METHOD_EXEC_INSIDE_UI_SYNC, "executeSync");
        put(METHOD_EXEC_OUTSIDE_UI, "executeOutside");
        put(METHOD_EXEC_FUTURE, "executeFuture");
    }};

    MethodDescriptor[] METHODS = new MethodDescriptor[]{
        MethodDescriptor.method(
            type(BOOLEAN),
            METHOD_IS_UITHREAD
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_EXEC_INSIDE_UI_ASYNC,
            args(type(JAVA_LANG_RUNNABLE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_EXEC_INSIDE_UI_SYNC,
            args(type(JAVA_LANG_RUNNABLE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_EXEC_OUTSIDE_UI,
            args(type(JAVA_LANG_RUNNABLE))
        ),
        MethodDescriptor.method(
            type(JAVA_UTIL_CONCURRENT_FUTURE, R),
            typeParams(R),
            METHOD_EXEC_FUTURE,
            args(type(JAVA_UTIL_CONCURRENT_EXECUTOR_SERVICE), type(GROOVY_LANG_CLOSURE, R))
        ),
        MethodDescriptor.method(
            type(JAVA_UTIL_CONCURRENT_FUTURE, R),
            typeParams(R),
            METHOD_EXEC_FUTURE,
            args(type(JAVA_UTIL_CONCURRENT_EXECUTOR_SERVICE), type(JAVA_UTIL_CONCURRENT_CALLABLE, R))
        ),
        MethodDescriptor.method(
            type(JAVA_UTIL_CONCURRENT_FUTURE, R),
            typeParams(R),
            METHOD_EXEC_FUTURE,
            args(type(GROOVY_LANG_CLOSURE, R))
        ),
        MethodDescriptor.method(
            type(JAVA_UTIL_CONCURRENT_FUTURE, R),
            typeParams(R),
            METHOD_EXEC_FUTURE,
            args(type(JAVA_UTIL_CONCURRENT_CALLABLE, R))
        )
    };
}

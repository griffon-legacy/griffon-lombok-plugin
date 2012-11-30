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

/**
 * @author Andres Almiray
 */
public interface EventPublisherConstants extends BaseConstants {
    String EVENT_ROUTER_TYPE = "griffon.core.EventRouter";
    String EVENT_PUBLISHER_TYPE = "griffon.core.EventPublisher";
    String EVENT_ROUTER_FIELD_NAME = "this$eventrouter";

    String METHOD_ADD_EVENT_LISTENER = "addEventListener";
    String METHOD_REMOVE_EVENT_LISTENER = "removeEventListener";
    String METHOD_PUBLISH_EVENT = "publishEvent";
    String METHOD_PUBLISH_EVENT_OUTSIDE_UI = "publishEventOutsideUI";
    String METHOD_PUBLISH_EVENT_ASYNC = "publishEventAsync";
    String METHOD_IS_EVENT_PUBLISHING_ENABLED = "isEventPublishingEnabled";
    String METHOD_SET_EVENT_PUBLISHING_ENABLED = "setEventPublishingEnabled";

    Map<String, String> METHOD_MAPPER = new LinkedHashMap<String, String>() {{
        put(METHOD_PUBLISH_EVENT, "publish");
        put(METHOD_PUBLISH_EVENT_OUTSIDE_UI, "publishOutsideUI");
        put(METHOD_PUBLISH_EVENT_ASYNC, "publishAsync");
        put(METHOD_IS_EVENT_PUBLISHING_ENABLED, "isEnabled");
        put(METHOD_SET_EVENT_PUBLISHING_ENABLED, "setEnabled");
    }};

    MethodDescriptor[] METHODS = new MethodDescriptor[]{
        MethodDescriptor.method(
            type(VOID),
            METHOD_ADD_EVENT_LISTENER,
            args(type(JAVA_LANG_OBJECT))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_ADD_EVENT_LISTENER,
            args(type(JAVA_LANG_STRING), type(GROOVY_LANG_CLOSURE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_ADD_EVENT_LISTENER,
            args(type(JAVA_LANG_STRING), type(GRIFFON_UTIL_RUNNABLEWITHARGS))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_REMOVE_EVENT_LISTENER,
            args(type(JAVA_LANG_OBJECT))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_REMOVE_EVENT_LISTENER,
            args(type(JAVA_LANG_STRING), type(GROOVY_LANG_CLOSURE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_REMOVE_EVENT_LISTENER,
            args(type(JAVA_LANG_STRING), type(GRIFFON_UTIL_RUNNABLEWITHARGS))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_PUBLISH_EVENT,
            args(type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_PUBLISH_EVENT,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_LIST))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_PUBLISH_EVENT_OUTSIDE_UI,
            args(type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_PUBLISH_EVENT_OUTSIDE_UI,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_LIST))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_PUBLISH_EVENT_ASYNC,
            args(type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_PUBLISH_EVENT_ASYNC,
            args(type(JAVA_LANG_STRING), type(JAVA_UTIL_LIST))
        ),
        MethodDescriptor.method(
            type(BOOLEAN),
            METHOD_IS_EVENT_PUBLISHING_ENABLED
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_SET_EVENT_PUBLISHING_ENABLED,
            args(type(BOOLEAN))
        )
    };
}

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

package lombok.core;

import lombok.core.util.MethodDescriptor;

import static lombok.core.util.MethodDescriptor.args;
import static lombok.core.util.MethodDescriptor.type;

/**
 * @author Andres Almiray
 */
public interface ResourcesAwareConstants extends BaseConstants {
    String RESOURCE_LOCATOR_TYPE = "org.codehaus.griffon.runtime.core.ResourceLocator";
    String RESOURCE_HANDLER_TYPE = "griffon.core.ResourceHandler";
    String RESOURCE_LOCATOR_FIELD_NAME = "this$resourceLocator";

    String METHOD_GET_RESOURCE_AS_URL = "getResourceAsURL";
    String METHOD_GET_RESOURCE_AS_STREAM = "getResourceAsStream";
    String METHOD_GET_RESOURCES = "getResources";

    MethodDescriptor[] METHODS = new MethodDescriptor[]{
        MethodDescriptor.method(
            type(JAVA_NET_URL),
            METHOD_GET_RESOURCE_AS_URL,
            args(type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type("java.io.InputStream"),
            METHOD_GET_RESOURCE_AS_STREAM,
            args(type(JAVA_LANG_STRING))
        ),
        MethodDescriptor.method(
            type(JAVA_UTIL_LIST, JAVA_NET_URL),
            METHOD_GET_RESOURCES,
            args(type(JAVA_LANG_STRING))
        )
    };
}

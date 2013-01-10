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

import java.lang.reflect.Modifier;

import static lombok.core.util.MethodDescriptor.*;

/**
 * @author Andres Almiray
 */
public interface VetoableConstants extends BindableConstants {
    String GRIFFON_CORE_VETOABLE_TYPE = "griffon.core.Vetoable";
    String VETOABLE_CHANGE_LISTENER_TYPE = "java.beans.VetoableChangeListener";
    String PROPERTY_VETO_EXCEPTION_TYPE = "java.beans.PropertyVetoException";
    String VETOABLE_CHANGE_SUPPORT_TYPE = "java.beans.VetoableChangeSupport";
    String VETOABLE_CHANGE_SUPPORT_FIELD_NAME = "this$vetoableChangeSupport";
    String VETOABLE_CHANGE_SUPPORT_FIELD_NAME_LOCK = "this$vetoableChangeSupportLock";

    String METHOD_GET_VETOABLE_CHANGE_SUPPORT = "getVetoableChangeSupport";
    String METHOD_ADD_VETOABLE_CHANGE_LISTENER = "addVetoableChangeListener";
    String METHOD_REMOVE_VETOABLE_CHANGE_LISTENER = "removeVetoableChangeListener";
    String METHOD_GET_VETOABLE_CHANGE_LISTENERS = "getVetoableChangeListeners";
    String METHOD_FIRE_VETOABLE_CHANGE = "fireVetoableChange";

    MethodDescriptor[] VETOABLE_METHODS = new MethodDescriptor[]{
        MethodDescriptor.method(
            type(VOID),
            METHOD_ADD_VETOABLE_CHANGE_LISTENER,
            args(type(VETOABLE_CHANGE_LISTENER_TYPE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_ADD_VETOABLE_CHANGE_LISTENER,
            args(
                type(JAVA_LANG_STRING),
                type(VETOABLE_CHANGE_LISTENER_TYPE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_REMOVE_VETOABLE_CHANGE_LISTENER,
            args(type(VETOABLE_CHANGE_LISTENER_TYPE))
        ),
        MethodDescriptor.method(
            type(VOID),
            METHOD_REMOVE_VETOABLE_CHANGE_LISTENER,
            args(
                type(JAVA_LANG_STRING),
                type(VETOABLE_CHANGE_LISTENER_TYPE))
        ),
        MethodDescriptor.method(
            type(VETOABLE_CHANGE_LISTENER_TYPE, 1),
            METHOD_GET_VETOABLE_CHANGE_LISTENERS
        ),
        MethodDescriptor.method(
            type(VETOABLE_CHANGE_LISTENER_TYPE, 1),
            METHOD_GET_VETOABLE_CHANGE_LISTENERS,
            args(type(JAVA_LANG_STRING))
        )
    };

    MethodDescriptor[] VETOABLE_FIRE_METHODS = new MethodDescriptor[]{
        MethodDescriptor.method(
            Modifier.PROTECTED,
            type(VOID),
            METHOD_FIRE_VETOABLE_CHANGE,
            args(type(PROPERTY_CHANGE_EVENT_TYPE)),
            throwing(type(PROPERTY_VETO_EXCEPTION_TYPE))
        ),
        MethodDescriptor.method(
            Modifier.PROTECTED,
            type(VOID),
            METHOD_FIRE_VETOABLE_CHANGE,
            args(
                type(JAVA_LANG_STRING),
                type(JAVA_LANG_OBJECT),
                type(JAVA_LANG_OBJECT)),
            throwing(type(PROPERTY_VETO_EXCEPTION_TYPE))
        )
    };
}

/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by getApplication()licable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 */
 
/**
 * @author Andres Almiray
 */
class LombokGriffonPlugin {
    // the plugin version
    String version = '0.2'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '0.9.5 > *'
    // the other plugins this plugin depends on
    Map dependsOn = [:]
    // resources that are included in plugin packaging
    List pluginIncludes = []
    // the plugin license
    String license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, gtk
    List toolkits = []
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-lombok-plugin'

    List authors = [
        [
            name: 'Andres Almiray',
            email: 'aalmiray@yahoo.com'
        ]
    ]
    String title = 'Enhance Java code with Lombok'
    // accepts Markdown syntax. See http://daringfireball.net/projects/markdown/ for details
    String description = '''
Allows bytecode manipulation at compile time using [Project Lombok][1]. Think of Lombok as [AST transformations][1] for Java source.

**Works with Javac only. Eclipse compiler support is forthcoming.**

Usage
-----

The Lombok plugin supports the following transformations

 * `@Threading` - modifies the method body to execute its contents in the appropriate threading context.
 * `@ThreadingAware` - injects the `griffon.core.ThreadingHandler` interface
 * `@ResourcesAware` - injects the `griffon.core.ResourceHandler` interface
 * `@EventPublisher` - injects the `griffon.core.EventPublisher` interface
 * `@Bindable` - injects the `griffon.core.Observable` interface.

In the case of `@Bindable` there are a couple of limitations at the moment that don't make it work exactly as its Groovy counterpart:

 * inheritance is not honored. That is, if the super class is already Observable this annotation will still inject the required methods.
 * the superclass will be set to `AbstractObservable` if the annotated class has no extends clause.
 * Java has no concept of properties like Groovy does, `@Bindable` expects all private fields to be properties.

These rules means that the following Java code

        @groovy.beans.Bindable
        public class Bean {
            private String input;
        }

gets compiled into

        public class Bean extends org.codehaus.griffon.runtime.core.AbstractObservable {
            public Bean();
            public void setInput(java.lang.String);
            public java.lang.String getInput();
        }

whereas this Java source

        package sample;
 
        import org.codehaus.griffon.runtime.core.AbstractGriffonModel;
 
        @groovy.beans.Bindable
        public class SampleModel extends AbstractGriffonModel {
            private String input;
        }

gets compiled to

        public class sample.SampleModel 
                extends org.codehaus.griffon.runtime.core.AbstractGriffonModel 
                implements griffon.core.Observable {
            public sample.SampleModel();
            public void addPropertyChangeListener(java.beans.PropertyChangeListener);
            public void addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener);
            public void removePropertyChangeListener(java.beans.PropertyChangeListener);
            public void removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener);
            public java.beans.PropertyChangeListener[] getPropertyChangeListeners();
            public java.beans.PropertyChangeListener[] getPropertyChangeListeners(java.lang.String);
            protected void firePropertyChange(java.lang.String, java.lang.Object, java.lang.Object);
            public void setInput(java.lang.String);
            public java.lang.String getInput();
        }

Additional transformations provided by [lombok-pg][3] are also available by installing this plugin.

[1]: http://projectlombok.org/
[2]: http://groovy.codehaus.org/Compile-time+Metaprogramming+-+AST+Transformations
[3]: https://github.com/peichhorn/lombok-pg
'''
}

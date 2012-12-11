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
    String version = '0.4'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '1.2.0 > *'
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
Allows bytecode manipulation at compile time using [Project Lombok][1]. Think
of Lombok as [AST transformations][2] for Java sources.

**Works with Javac only. Eclipse compiler support is forthcoming.**

Usage
-----

The Lombok plugin supports the following transformations

 * `@Threading` - modifies the method body to execute its contents in the
   appropriate threading context.
 * `@ThreadingAware` - injects the [griffon.core.ThreadingHandler][4] interface
 * `@ResourcesAware` - injects the [griffon.core.ResourceHandler][5] interface
 * `@EventPublisher` - injects the [griffon.core.EventPublisher][6] interface
 * `@Bindable` - injects the [griffon.core.Observable][7] interface.
 * `@MVCAware` - injects the [griffon.core.MVCHandler][8] interface
 * `@MessageSourceAware` - injects the [griffon.core.i18n.MessageSource][9]
   interface
 * `@ResourceResolverAware` - injects the [griffon.core.resources.ResourceResolver][10]
   interface

In the case of `@Bindable` there are a couple of limitations at the moment that
don't make it work exactly as its Groovy counterpart:

 * inheritance is not honored. That is, if the super class is already `Observable`
   this annotation will still inject the required methods.
 * the superclass will be set to `AbstractObservable` if the annotated class
   has no extends clause.
 * Java has no concept of properties like Groovy does, `@Bindable` expects all
   private fields to be properties.

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

Additional transformations provided by [lombok-pg][3] are also available by
installing this plugin.

Tool Support
------------

### JavaC

Support for this compiler is provided out-of-the-box by the command line tools.
There's no additional configuration required.

### Eclipse

Follow these steps to setup Lombok in Eclipse

 1. Make sure the project has the required files to be opened as an Eclipse
    Project. Simply call the following command

         griffon integrate-with --eclipse

 2. Install the [eclipse-support][11] plugin and update the `.classpath` file

         griffon install-plugin eclipse-support
         griffon eclipse-update

 3. Open the project in Eclipse
 4. Locate the `lombok-x.y.z.jar` in the project libraries. Right click on it,
    run it as a Java application. Select `lombok.core.Main` as the class to
    launch. Follow the on-screen instructions. Make a not of the install path
    as you'll need it in the next step. Shutdown Eclipse.
 5. Go to the path where the `lombok.jar` was copied. This path is either found
    inside the Eclipse installation directory or in your local settings. Copy
    the following files from the project's working directory

         $ cp $USER_HOME/.griffon/<version>/projects/<project>/plugins/lombok-<version>/dist/griffon-lombok-compile-<version>.jar .
         $ cp $USER_HOME/.ivy2/cache/com.github.peichhorn/lombok-pg/jars/lombok-pg-<version>.jar .

 6. Edit the launch script for Eclipse and tweak the boothclasspath entry so
    that includes the file you just copied

         -Xbootclasspath/a:lombok.jar:lombok-pg-<version>.jar:griffon-lombok-compile-<version>.jar

 7. Launch Eclipse once more.

### NetBeans

Follow the instructions found in [Annotation Processors Support in the NetBeans
IDE, Part I: Using Project Lombok][12]. You may need to specify
`lombok.core.AnnotationProcessor` in the list of Annotation Processors.

### Intellij IDEA

Follow these steps to setup Lombok in Intellij IDEA

 1. Download the latest stable release of [lombok-intellij-plugin][7] as a
    zip file.
 2. Open up the Preferences dialog in IntelliJ IDEA
 3. Go to the Plugins page. Click on the "Install plugin from disk..." button.
    Select the zip file you just downloaded.
 4. Shutdown IntelliJ IDEA; locate the directory where the lombok plugin was
    installed. This directory is usually found somewhere inside your personal
    settings. In OSX for example it would be located in
    `$USER_HOME/Library/Application Support/IntelliJIdea11/lombok-plugin`.
 5. Copy `griffon-lombok-compile-<version>.jar` to the `lib` directory

         $ pwd
           $USER_HOME/Library/Application Support/IntelliJIdea11/lombok-plugin
         $ cp $USER_HOME/.griffon/<version>/projects/<project>/plugins/lombok-<version>/dist/griffon-lombok-compile-<version>.jar lib

 6. Launch IntelliJ IDEA once more.


[1]: http://projectlombok.org/
[2]: http://groovy.codehaus.org/Compile-time+Metaprogramming+-+AST+Transformations
[3]: https://github.com/peichhorn/lombok-pg
[4]: http://griffon.codehaus.org/guide/latest/api/griffon/core/ThreadingHandler.html
[5]: http://griffon.codehaus.org/guide/latest/api/griffon/core/ResourceHandler.html
[6]: http://griffon.codehaus.org/guide/latest/api/griffon/core/EventPublisher.html
[7]: http://griffon.codehaus.org/guide/latest/api/griffon/core/Observable.html
[8]: http://griffon.codehaus.org/guide/latest/api/griffon/core/MVCHandler.html
[9]: http://griffon.codehaus.org/guide/latest/api/griffon/core/i18n/MessageSource.html
[10]: http://griffon.codehaus.org/guide/latest/api/griffon/core/resources/ResourceResolver.html
[11]: /plugin/eclipse-support
[12]: http://netbeans.org/kb/docs/java/annotations-lombok.html
[13]: http://code.google.com/p/lombok-intellij-plugin
'''
}

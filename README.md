
Enhance Java code with Lombok
-----------------------------

Plugin page: [http://artifacts.griffon-framework.org/plugin/lombok](http://artifacts.griffon-framework.org/plugin/lombok)


Allows bytecode manipulation at compile time using [Project Lombok][1]. Think
of Lombok as [AST transformations][2] for Java sources.

**Supports Javac and Eclipse JDT. Works with Intellij IDEA, Eclipse and NetBeans.**

Usage
-----

The Lombok plugin supports the following transformations

 * `@ThreadingAware` - injects the `griffon.core.ThreadingHandler` interface
 * `@ResourcesAware` - injects the `griffon.core.ResourceHandler` interface
 * `@EventPublisher` - injects the `griffon.core.EventPublisher` interface
 * `@Bindable` - injects the `griffon.core.Observable` interface
 * `@Vetoable` - injects the `griffon.core.Vetoable` interface
 * `@MVCAware` - injects the `griffon.core.MVCHandler` interface
 * `@MessageSourceAware` - injects the `griffon.core.i18n.MessageSource` interface
 * `@ResourceResolverAware` - injects the `griffon.core.resources.ResourceResolver`
   interface

In the case of `@Bindable` and `@Vetoable` there are a couple of limitations at
the moment that don't make it work exactly as its Groovy counterpart:

 * Java has no concept of properties like Groovy does, `@Bindable` expects all
   private fields to be properties.

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

 2. Install the [eclipse-support][4] plugin and update the `.classpath` file

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
IDE, Part I: Using Project Lombok][5]. You may need to specify
`lombok.core.AnnotationProcessor` in the list of Annotation Processors.

### Intellij IDEA

Follow these steps to setup Lombok in Intellij IDEA

 1. Download the latest stable release of [lombok-intellij-plugin][6] as a
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
[4]: /plugin/eclipse-support
[5]: http://netbeans.org/kb/docs/java/annotations-lombok.html
[6]: http://code.google.com/p/lombok-intellij-plugin

### Building

This project requires all of its dependencies be available from maven compatible repositories.
Some of these dependencies have not been pushed to the Maven Central Repository, however you
can obtain them from [lombok-dev-deps][lombok-dev-deps].

Follow the instructions found there to install the required dependencies into your local Maven
repository before attempting to build this plugin.

[lombok-dev-deps]: https://github.com/aalmiray/lombok-dev-deps
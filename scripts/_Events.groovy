/*
 * Copyright 2012-2013 the original author or authors.
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

eventPost_package_pluginEnd = {
    if (!compilingPlugin('lombok')) return

    File tmpdir = new File("${projectTargetDir}/tmp-unpack")
    try {
        ant.unjar(dest: tmpdir, src: "${griffonHome}/dist/griffon-cli-${griffonVersion}.jar", overwrite: true) {
            patternset {
                include(name: "griffon/transform/**")
            }
        }
        ant.unjar(dest: tmpdir, src: "${griffonHome}/lib/groovy-all-${griffonSettings.groovyVersion}.jar", overwrite: true) {
            patternset {
                include(name: "groovy/beans/Bindable.class")
                include(name: "groovy/beans/Vetoable.class")
                include(name: "groovy/beans/ListenerList.class")
                include(name: "org/codehaus/groovy/transform/GroovyASTTransformationClass.class")
            }
        }
        File compileJar = new File("${artifactPackageDirPath}/dist/griffon-${pluginName}-compile-${pluginVersion}.jar")
        ant.jar(destfile: compileJar, update: true, filesonly: true) {
            fileset(dir: tmpdir)
        }
    } finally {
        ant.delete(dir: tmpdir.path)
    }
}
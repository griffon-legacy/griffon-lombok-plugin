griffon.project.dependency.resolution = {
    inherits "global"
    log "warn"
    repositories {
        griffonHome()
        mavenCentral()
        mavenLocal()
        mavenRepo 'http://repository.jetbrains.com/releases'
    }
    dependencies {
        build "org.projectlombok:lombok:0.11.6",
              "com.github.peichhorn:lombok-pg:0.11.3"
        build('org.eclipse.jdt:org.eclipse.jdt.core:3.6.0.v_A58') {
            export = false
        }
        String lombokIdea = '0.5'
        build("de.plushnikov.lombok-intellij-plugin:processor-api:$lombokIdea",
            "de.plushnikov.lombok-intellij-plugin:processor-core:$lombokIdea",
            "de.plushnikov.lombok-intellij-plugin:intellij-facade-factory:$lombokIdea",
            "de.plushnikov.lombok-intellij-plugin:intellij-facade-api:$lombokIdea",
            "de.plushnikov.lombok-intellij-plugin:intellij-facade-9:$lombokIdea",
            "de.plushnikov.lombok-intellij-plugin:intellij-facade-10:$lombokIdea",
            "de.plushnikov.lombok-intellij-plugin:intellij-facade-11:$lombokIdea") {
            export = false
            transitive = false
        }
        String ideaVersion = '11.1.4'
        build("org.jetbrains.idea:idea-openapi:$ideaVersion",
            "org.jetbrains.idea:extensions:$ideaVersion",
            "org.jetbrains.idea:util:$ideaVersion",
            "org.jetbrains.idea:annotations:$ideaVersion")
    }
}


griffon {
    doc {
        logo = '<a href="http://griffon.codehaus.org" target="_blank"><img alt="The Griffon Framework" src="../img/griffon.png" border="0"/></a>'
        sponsorLogo = "<br/>"
        footer = "<br/><br/>Made with Griffon (@griffon.version@)"
    }
}

log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    appenders {
        console name: 'stdout', layout: pattern(conversionPattern: '%d [%t] %-5p %c - %m%n')
    }

    error 'org.codehaus.griffon',
          'org.springframework',
          'org.apache.karaf',
          'groovyx.net'
    warn  'griffon'
}

griffon.project.dependency.resolution = {
    inherits "global"
    log "warn" 
    repositories {
        griffonHome()
        mavenRepo 'http://repository.sonatype.org/content/groups/public'
    }
    dependencies {
/*
        String lombokVersion = '0.11.2'
        build "org.projectlombok:lombok:$lombokVersion",
              "com.github.peichhorn:lombok-pg:$lombokVersion"
*/
        build "org.projectlombok:lombok:0.11.4",
              "com.github.peichhorn:lombok-pg:0.11.3"
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

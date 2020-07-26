import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.2"

project {

    vcsRoot(HttpsGithubComGbmeukSpringPetclinicRefsHeadsMain)

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(HttpsGithubComGbmeukSpringPetclinicRefsHeadsMain)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = defaultProvidedVersion()
        }
    }

    triggers {
        vcs {
        }
    }
})

object HttpsGithubComGbmeukSpringPetclinicRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/gbmeuk/spring-petclinic#refs/heads/main"
    url = "https://github.com/gbmeuk/spring-petclinic"
    branch = "refs/heads/main"
    authMethod = password {
        userName = "gbmeuk"
        password = ""
    }
})

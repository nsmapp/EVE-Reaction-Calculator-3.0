pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "EVE Reaction Calculator"

includeBuild("build-plugin")

include(":app")
include(":uikit")
include(":domain")

include(":data:database-datasource")
include(":navigation")
include(":feature-reactions")
include(":feature-library")
include(":feature-settings")
include(":feature-main")
include(":base")
include(":feature-reactor")
include(":di")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SonoraFlow"
include(":app")

// Core Modules
include(":core:ui")
include(":core:common")
include(":core:data")
include(":core:model")
include(":core:audio")
include(":core:network")
include(":core:database")
include(":core:ai")

// Feature Modules
include(":feature:home")
include(":feature:player")
include(":feature:library")
include(":feature:settings")
include(":feature:onboarding")

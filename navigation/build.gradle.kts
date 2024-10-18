plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
    id("common.feature")
}

android {
    namespace = "be.nepravsky.sm.evereactioncalculator.navigations"

    dependencies {
        implementation(project(":base"))
        implementation(project(":feature-main"))
        implementation(project(":feature-library"))
        implementation(project(":feature-reactions"))
        implementation(project(":feature-reactor"))
        implementation(project(":feature-settings"))
        implementation(project(":feature-project-builder"))
    }
}


plugins {
    alias(libs.plugins.androidLibrary)
    id("feature")
}


android{
    namespace = "be.nepravsky.sm.evereactioncalculator.di"

    dependencies {
        implementation(project(":data:database-datasource"))
        implementation(project(":data:network-source"))
        implementation(project(":navigation"))
        implementation(project(":feature-library"))
        implementation(project(":feature-main"))
        implementation(project(":feature-reactions"))
        implementation(project(":feature-reactor"))
        implementation(project(":feature-settings"))
    }
}
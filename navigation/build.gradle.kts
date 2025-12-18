plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
    id("common.feature")
}

android {
    namespace = "be.nepravsky.sm.evereactioncalculator.navigations"

    dependencies {
        implementation(project(":base"))
        implementation(project(":uikit"))
        implementation(project(":feature-main"))
        implementation(project(":feature-library"))
        implementation(project(":feature-reactions"))
        implementation(project(":feature-reactor"))
        implementation(project(":feature-settings"))
        implementation(project(":feature-project-builder"))
        implementation(project(":feature-search-settings"))

        implementation(libs.androidx.navigation3.ui)
        implementation(libs.androidx.navigation3.runtime)
//        implementation(libs.androidx.lifecycle.viewmodel.navigation3)
        implementation(libs.androidx.material3.adaptive.navigation3)
        implementation(libs.kotlinx.serialization.core)
    }
}


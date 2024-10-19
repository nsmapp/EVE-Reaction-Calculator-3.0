import ext.getLibs

plugins {
    alias(libs.plugins.androidLibrary)
    id("common.feature")
}

android{
    namespace = "be.nepravsky.sm.evereactioncalculator.uikit"

}

dependencies {
    implementation(project(":base"))
    implementation(getLibs().coil.coil)
    debugImplementation(libs.ui.tooling)
}

plugins {
    alias(libs.plugins.androidLibrary)
    id("common.feature")
}

android{
    namespace = "be.nepravsky.sm.evereactioncalculator.uikit"

}

dependencies {
    debugImplementation(libs.ui.tooling)
}

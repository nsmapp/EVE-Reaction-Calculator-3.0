plugins {
    alias(libs.plugins.androidLibrary)
    id("feature")
}

android {
    namespace = "be.nepravsky.sm.evereactioncalculator.library"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }


}
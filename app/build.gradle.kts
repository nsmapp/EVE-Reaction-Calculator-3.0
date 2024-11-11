import ext.getLibs

plugins {
    id("com.android.application")
    id("common.feature")
    id("base.koin.config")
}


android {
    defaultConfig {
        applicationId = "be.nepravsky.sm.evereactioncalculator"
    }
    namespace = "be.nepravsky.sm.evereactioncalculator"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }


    dependencies {
        implementation(getLibs().coil.coil)
        implementation(project(":base"))
        implementation(project(":uikit"))
        implementation(project(":domain"))
        implementation(project(":di"))
        implementation(project(":navigation"))
    }
}
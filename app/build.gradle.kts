import ext.getLibs

plugins {
    id("com.android.application")
    id("common.feature")
    id("base.koin.config")
}


android {
    defaultConfig {
        applicationId = "by.nepravsky.sm.evereactioncalculator"
        versionCode = 30000
        versionName = "3.0c"
    }
    namespace = "be.nepravsky.sm.evereactioncalculator"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = true
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
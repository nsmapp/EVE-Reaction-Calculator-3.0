import ext.getLibs
import java.util.Properties

plugins {
    id("com.android.application")
    id("common.feature")
    id("base.koin.config")
//    alias(libs.plugins.google.gms.google.services)
//    alias(libs.plugins.google.firebase.crashlytics)
}


android {
    defaultConfig {
        targetSdk = libs.versions.compileSDk.get().toInt()
        applicationId = "by.nepravsky.sm.evereactioncalculator"
        versionCode = 30001
        versionName = "3.0d"
    }
    namespace = "be.nepravsky.sm.evereactioncalculator"

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    signingConfigs {
        create("release") {
            keyAlias = properties.getProperty("SIGN_ALIAS", "")
            keyPassword =  properties.getProperty("SIGN_ALIAS_PASS", "")
            storeFile = file("../key/evereactioncalculator.jks")
            storePassword =  properties.getProperty("SIGN_STORE", "")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }

        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }

    dependencies {
//        implementation(libs.firebase.crashlytics)
//        implementation(libs.firebase.analytics)

        implementation(getLibs().coil.coil)
        implementation(project(":base"))
        implementation(project(":uikit"))
        implementation(project(":domain"))
        implementation(project(":di"))
        implementation(project(":navigation"))
    }
}

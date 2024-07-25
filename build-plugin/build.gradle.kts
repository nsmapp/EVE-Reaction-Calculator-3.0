import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

repositories{
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://plugins.gradle.org/m2/")
}

dependencies{
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}


java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.java.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.java.get())
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = libs.versions.java.get()
}

gradlePlugin{
    plugins{
        register("feature"){
            id = "feature"
            implementationClass = "plugin.FeatureConventionPlugin"
        }

        register("common.feature"){
            id = "common.feature"
            implementationClass = "plugin.CommonFeatureConventionPlugin"
        }

        register("base.config"){
            id = "base.config"
            implementationClass = "plugin.BaseConfigConventionPlugin"
        }

        register("base.koin.config"){
            id = "base.koin.config"
            implementationClass = "plugin.KoinConventionPlugin"
        }
    }
}
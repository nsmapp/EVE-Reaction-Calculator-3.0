plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("base.koin.config")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
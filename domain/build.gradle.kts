plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("base.koin.config")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
plugins {
    alias(libs.plugins.androidLibrary)
    id("base.config")
    id("base.koin.config")
    alias(libs.plugins.kotlin.serialization)
}

android{
    namespace = "be.nepravsky.sm.network"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logs)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.client.serialization.json)
    implementation(project(":domain"))
}
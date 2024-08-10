plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqlDelite)
    id("base.config")
    id("base.koin.config")
}

sqldelight {
    databases {
        linkSqlite.set(false)
        create("Database") {
            packageName.set("be.nepravsky.sm.database")
            version = 1
        }
    }
}

android{
    namespace = "be.nepravsky.sm.database"
}

dependencies {
    implementation(libs.sqlDelight.core)
    implementation(libs.sqlDelight.extensions)
    implementation(libs.sqlDelight.driver.android)
    implementation(libs.sqlDelight.pribitive.adapters)
    implementation(project(":domain"))
}
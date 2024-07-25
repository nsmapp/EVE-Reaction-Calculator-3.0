plugins {
    id("com.android.application")
    id("feature")
    id("base.koin.config")
}


android{
    defaultConfig{
        applicationId = "be.nepravsky.sm.evereactioncalculator"
    }
    namespace = "be.nepravsky.sm.evereactioncalculator"

    dependencies {
        implementation(project(":di"))
        implementation(project(":navigation"))
    }
}
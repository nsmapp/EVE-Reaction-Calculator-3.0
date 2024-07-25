import com.android.build.gradle.BaseExtension
import ext.getLibs
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.kotlin


plugins{
    kotlin("android")
}



configure<BaseExtension> {

    compileSdkVersion(getLibs().versions.compileSDk.get().toInt())

    defaultConfig{

        minSdk = getLibs().versions.minSDK.get().toInt()
        targetSdk = getLibs().versions.compileSDk.get().toInt()

        versionCode = getLibs().versions.versionCode.get().toInt()
        versionName = getLibs().versions.versionName.get()
    }


    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(getLibs().versions.java.get())
        targetCompatibility = JavaVersion.toVersion(getLibs().versions.java.get())
    }
}
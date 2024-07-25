package plugin

import ext.getLibs
import ext.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CommonFeatureConventionPlugin : Plugin<Project> {


    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("convention-base-config")
                apply("convention-compose")
                apply("base.koin.config")
                apply(getLibs().plugins.compose.compiler.get().pluginId)
            }

            dependencies {
                implementation(getLibs().androidx.lifecycle.runtime.ktx)
                implementation(getLibs().androidx.core.ktx)
                implementation(getLibs().androidx.lifecycle.runtime.ktx)
                implementation(getLibs().androidx.activity.compose)
                implementation(platform(getLibs().androidx.compose.bom))
                implementation(getLibs().androidx.ui.graphics)
                implementation(getLibs().androidx.ui.tooling.preview)
                implementation(getLibs().androidx.material3)
                implementation(getLibs().accompanist.systemuicontroller)
                implementation(getLibs().koin.compose)
                implementation(getLibs().koin.android)
                implementation(getLibs().kotlinx.coroutines.android)
                implementation(getLibs().decompose.core)
                implementation(getLibs().decompose.extension)
                implementation(getLibs().decompose.android.extension)
            }
        }
    }

}
package plugin

import ext.getLibs
import ext.implementation
import ext.ksp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KoinConventionPlugin: Plugin<Project>{


    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply(getLibs().plugins.ksp.get().pluginId)

            }

            dependencies {
                implementation(getLibs().koin.core)
                implementation(getLibs().koin.coroutines)
                implementation(getLibs().koin.annotations)
                ksp(getLibs().koin.ksp.compiler)
                implementation(getLibs().kotlinx.coroutines.core)
            }
        }

    }
}
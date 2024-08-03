package plugin

import ext.getLibs
import ext.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class FeatureConventionPlugin : Plugin<Project> {


    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("common.feature")
                apply("base.koin.config")
            }

            dependencies {
                implementation(getLibs().coil.coil)
                implementation(project(":base"))
                implementation(project(":uikit"))
                implementation(project(":domain"))
            }
        }
    }

}
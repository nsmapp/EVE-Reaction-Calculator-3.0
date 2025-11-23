package plugin

import ext.getLibs
import ext.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType

class FeatureConventionPlugin : Plugin<Project> {


    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("common.feature")
                apply("base.koin.config")

                tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
                    compilerOptions {

                        freeCompilerArgs.addAll(
                            listOf(
                                "-P",
                                "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${rootProject.buildDir}/compose_metrics"
                            )
                        )
                        freeCompilerArgs.addAll(
                            listOf(
                                "-P",
                                "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${rootProject.buildDir}/compose_reports"
                            )
                        )
                    }

                }

                dependencies {
                    implementation(getLibs().immutable.collections.list)
                    implementation(getLibs().coil.coil)
                    implementation(project(":base"))
                    implementation(project(":uikit"))
                    implementation(project(":domain"))
                }
            }
        }

    }
}
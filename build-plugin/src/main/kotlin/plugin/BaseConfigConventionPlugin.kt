package plugin

import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project

class BaseConfigConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("convention-base-config")
            }
        }
    }
}
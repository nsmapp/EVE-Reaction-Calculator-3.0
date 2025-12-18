package be.nepravsky.sm.evereactioncalculator.projectbuilder

import be.nepravsky.builder.BuilderRouter

class ProjectBuilderRouterImpl(
    private val onNavigateSearchSettings: (() -> Unit),
    private val onNavigateBack: (() -> Unit),
): BuilderRouter {
    override fun openSearchSettings() {
        onNavigateSearchSettings()
    }

    override fun onBackPressed() {
        onNavigateBack()
    }
}
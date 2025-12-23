package be.nepravsky.sm.evereactioncalculator.reactions

import be.nepravsky.sm.evereactioncalculator.reactions.contract.ReactionsRouter

class ReactionsRouterImpl(
    private val onNavigateSearchSettings: (() -> Unit),
    private val onNavigateReactor: ((Long, Boolean) -> Unit),
): ReactionsRouter{

    override fun openSearchSettings() {
        onNavigateSearchSettings()
    }

    override fun buildReaction(id: Long, isSingleReaction: Boolean) {
        onNavigateReactor(id, isSingleReaction)
    }
}

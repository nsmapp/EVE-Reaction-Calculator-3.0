package be.nepravsky.sm.evereactioncalculator.news.reactions

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import be.nepravsky.sm.evereactioncalculator.news.Navigator
import be.nepravsky.sm.evereactioncalculator.news.reactor.ReactorDest
import be.nepravsky.sm.evereactioncalculator.news.searchsettings.SearchSettingsDest
import be.nepravsky.sm.evereactioncalculator.reactions.ReactionsScreen

fun EntryProviderScope<NavKey>.reaction(navigator: Navigator) {

    entry<ReactionsDest.Reactions> {
        ReactionsScreen(
            onBuildReaction = { reactionId, isSingleReaction ->
                navigator.navigate(
                    ReactorDest.root(
                        reactionId,
                        isSingleReaction
                    )
                )
            },
            onOpenSearchSettings = { navigator.navigate(SearchSettingsDest.root()) },
        )
    }

}
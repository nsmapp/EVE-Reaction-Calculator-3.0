package be.nepravsky.sm.evereactioncalculator.model

sealed class ReactorSideEffect {

    data class ShareReaction(val text: String): ReactorSideEffect()

    data object PriceUpdateError: ReactorSideEffect()
}
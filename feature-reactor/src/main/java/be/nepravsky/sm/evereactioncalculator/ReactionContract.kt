package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.domain.model.query.ReactionQuery

interface ReactionContract {


    /**
     * Set runs
     *
     * @param run  run count
     */
    fun setRuns(run: Long)

    /**
     * Set me
     *
     * @param me base reaction material effectivity
     */
    fun setMe(me: Double)

    /**
     * Set sub me
     *
     * @param subMe sub material material effectivity
     */
    fun setSubMe(subMe: Double)

    /**
     * Make reaction
     *
     * @param query
     */
    fun makeReaction(query: List<ReactionQuery>)

    /**
     * Show s hare dialog
     *
     */
    fun showShareDialog()

    /**
     * Hide share dialog
     *
     */
    fun hideShareDialog()

    /**
     * Share as simple text
     *
     */
    fun shareAsSimpleText(isBaseMaterials: Boolean)

    /**
     * Share as eve note text
     *
     */
    fun shareAsEveNoteText(isBaseMaterials: Boolean)
}
package be.nepravsky.sm.evereactioncalculator.about

import android.content.Context

interface AboutRouter {

    /**
     * On finish
     *
     */
    fun onFinish()

    /**
     * Open git hub link
     *
     */
    fun openGitHubLink(context: Context, url: String)

    /**
     * Send email
     *
     */
    fun sendEmail(context: Context, mail: String, subject: String)

}
package recloudstream

import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin
import android.content.Context

@CloudstreamPlugin
class MoviPkPlugin : Plugin() {
    override fun load(context: Context) {
        // Yahan hum MoviPKProvider ko register kar rahe hain
        registerMainAPI(MoviPKProvider())
    }
}

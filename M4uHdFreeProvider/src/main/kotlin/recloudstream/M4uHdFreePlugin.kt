package recloudstream

import com.lagradost.cloudstream3.plugins.BasePlugin
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin

@CloudstreamPlugin
class M4uHdFreePlugin : BasePlugin() {
    override fun load() {
        registerMainAPI(M4uHdFreeProvider())
    }
}

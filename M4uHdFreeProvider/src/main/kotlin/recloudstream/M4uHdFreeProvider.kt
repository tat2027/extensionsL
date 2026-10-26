package recloudstream

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*

class M4uHdFreeProvider : MainAPI() {
    override var mainUrl = "https://m4uhdfree.net"
    override var name = "M4uHDFree"
    override var supportedTypes = setOf(TvType.Movie, TvType.TvSeries)
    override var lang = "en"
    override var hasMainPage = true

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val document = app.get(mainUrl).document
        val home = document.select("div.item").mapNotNull {
            val title = it.selectFirst("a.title")?.text() ?: return@mapNotNull null
            val href = it.selectFirst("a")?.attr("href") ?: return@mapNotNull null
            val poster = it.selectFirst("img")?.attr("src")
            newMovieSearchResponse(title, href, TvType.Movie) {
                this.posterUrl = poster
            }
        }
        return newHomePageResponse(listOf(HomePageList("Latest Movies", home)))
    }

    override suspend fun search(query: String): List<SearchResponse> {
        val url = "$mainUrl/search/$query.html"
        val document = app.get(url).document
        return document.select("div.item").mapNotNull {
            val title = it.selectFirst("a.title")?.text() ?: return@mapNotNull null
            val href = it.selectFirst("a")?.attr("href") ?: return@mapNotNull null
            val poster = it.selectFirst("img")?.attr("src")
            newMovieSearchResponse(title, href, TvType.Movie) {
                this.posterUrl = poster
            }
        }
    }

    override suspend fun load(url: String): LoadResponse {
        val document = app.get(url).document
        val title = document.selectFirst("h1")?.text().toString()
        val poster = document.selectFirst("div.poster img")?.attr("src")
        val description = document.selectFirst("div.description")?.text()

        return newMovieLoadResponse(title, url, TvType.Movie, url) {
            this.posterUrl = poster
            this.plot = description
        }
    }

    override suspend fun loadLinks(data: String, isCasting: Boolean, subtitleCallback: (SubtitleFile) -> Unit, callback: (ExtractorLink) -> Unit): Boolean {
        return true
    }
}

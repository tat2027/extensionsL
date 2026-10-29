package recloudstream

import com.lagradost.cloudstream3.HomePageResponse
import com.lagradost.cloudstream3.LoadResponse
import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.MainPageRequest
import com.lagradost.cloudstream3.SearchQuality
import com.lagradost.cloudstream3.SearchResponse
import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.TvType
import com.lagradost.cloudstream3.addDubStatus
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.mainPageOf
import com.lagradost.cloudstream3.newMovieSearchResponse
import com.lagradost.cloudstream3.newTvSeriesSearchResponse
import com.lagradost.cloudstream3.newHomePageResponse
import com.lagradost.cloudstream3.newMovieLoadResponse
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.loadExtractor
import org.jsoup.nodes.Element

class MoviPKProvider : MainAPI() {
    override var mainUrl = "https://www.movi.pk"
    override var name = "MoviPK"
    override var lang = "en"
    override val hasMainPage = true
    override val hasDownloadSupport = true
    override val hasQuickSearch = false
    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries
    )
    override val mainPage = mainPageOf(
        "/genre/featured/" to "Featured",
        "/movies/" to "Latest",
        "/genre/indian-movies/" to "Indian",
        "/genre/hollywood-hindi-dubbed-movies/" to "Hollywood Hindi Dubbed",
        "/genre/south-indian-hindi-dubbed-movies/" to "South Indian Hindi",
        "/genre/tv-series/" to "Web Series [Indian]",
        "/genre/web-series-hindi-dubbed/" to "Web Series [Dub]",
        "/genre/hollywood-movies/" to "Hollywood",
        "/genre/anime/" to "Anime"
    )

    override suspend fun getMainPage(
        page: Int,
        request: MainPageRequest
    ): HomePageResponse {
        val doc = app.get(
            "$mainUrl${request.data}page/$page",
            cacheTime = 60,
            allowRedirects = true,
            timeout = 30
        ).document
        val home = doc.select(".ml-mask.jt").mapNotNull { toResult(it) }
        return newHomePageResponse(request.name, home, true)
    }

    private fun toResult(post: Element): SearchResponse? {
        val aTag = post.selectFirst("a:nth-child(1)") ?: return null
        val title = aTag.attr("oldtitle").ifEmpty { aTag.attr("title") }.ifEmpty { aTag.text() }
        val url = aTag.attr("href")
        if (url.isNullOrEmpty()) return null

        val check = title.lowercase()
        val quality = aTag.selectFirst("span")?.text()

        // Detect if it's a series or movie based on URL or title keywords
        val isSeries = check.contains("season") || check.contains("episode") || url.contains("tv-series")
        val tvType = if (isSeries) TvType.TvSeries else TvType.Movie

        return if (isSeries) {
            newTvSeriesSearchResponse(title, url, tvType) {
                this.posterUrl = post.selectFirst(".lazy.thumb.mli-thumb")?.attr("data-original")
                this.quality = getSearchQuality(quality)
                addDubStatus(
                    dubExist = "dubbed" in check || "dual audio" in check || "multi audio" in check,
                    subExist = false
                )
            }
        } else {
            newMovieSearchResponse(title, url, tvType) {
                this.posterUrl = post.selectFirst(".lazy.thumb.mli-thumb")?.attr("data-original")
                this.quality = getSearchQuality(quality)
                addDubStatus(
                    dubExist = "dubbed" in check || "dual audio" in check || "multi audio" in check,
                    subExist = false
                )
            }
        }
    }

    override suspend fun search(query: String): List<SearchResponse> {
        val doc = app.get("$mainUrl/?s=$query", cacheTime = 60, timeout = 30).document
        return doc.select(".ml-mask.jt").mapNotNull { toResult(it) }
    }

    override suspend fun load(url: String): LoadResponse {
        val doc = app.get(
            url,
            cacheTime = 60,
            timeout = 30,
            headers = mapOf(
                "user-agent" to "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36"
            )).document
        val title = doc.select(".mvic-desc > h3:nth-child(1)").text()
        val image = doc.selectFirst(".hidden.lazyload")?.attr("data-src")
        val plot = doc.select("p.f-desc:nth-child(1)").text().replace(title, "")
        
        var dataUrl = ""
        doc.select("div.btn-group:nth-child(2) a").forEach {
            val link = it.attr("href")
            if (link.contains("https://listeamed.net")) dataUrl = link
            else if (link.contains("https://bembed.net")) dataUrl =
                link.replace("https://bembed.net", "https://listeamed.net")
        }
        dataUrl = dataUrl.replace("/v/", "/e/")
        
        return newMovieLoadResponse(title, url, TvType.Movie, dataUrl) {
            this.posterUrl = image
            this.plot = plot
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        loadExtractor(data, data, subtitleCallback, callback)
        return true
    }

    private fun getSearchQuality(check: String?): SearchQuality? {
        val lowercaseCheck = check?.lowercase() ?: return null
        return when {
            lowercaseCheck.contains("webrip") || lowercaseCheck.contains("web-dl") -> SearchQuality.WebRip
            lowercaseCheck.contains("bluray") -> SearchQuality.BlueRay
            lowercaseCheck.contains("hdts") || lowercaseCheck.contains("hdcam") || lowercaseCheck.contains("hdtc") -> SearchQuality.HdCam
            lowercaseCheck.contains("dvd") -> SearchQuality.DVD
            lowercaseCheck.contains("cam") -> SearchQuality.Cam
            lowercaseCheck.contains("camrip") || lowercaseCheck.contains("rip") -> SearchQuality.CamRip
            lowercaseCheck.contains("hdrip") || lowercaseCheck.contains("hd") || lowercaseCheck.contains("hdtv") -> SearchQuality.HD
            lowercaseCheck.contains("telesync") -> SearchQuality.Telesync
            lowercaseCheck.contains("telecine") -> SearchQuality.Telecine
            else -> null
        }
    }
}

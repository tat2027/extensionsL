package com.lagradost.cloudstream3

import com.lagradost.cloudstream3.utils.*
import org.jsoup.nodes.Element

class hdhub4u : MainAPI() {
    override var mainUrl = "https://new9.hdhub4u.cl"
    override var name = "HDHub4u"
    override val hasMainPage = true
    override var lang = "hi"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    // 1. Home Page / Latest Releases
    override suspend fun getMainPage(page: Int, request: HomePageRequest): HomePageResponse {
        val document = app.get(mainUrl).document
        val home = document.select("div.movie-track a.movie-card").mapNotNull { element ->
            val href = fixUrl(element.attr("href"))
            val title = element.selectFirst("img")?.attr("alt") ?: "Movie"
            val posterUrl = fixUrl(element.selectFirst("img")?.attr("src") ?: "")

            newMovieSearchResponse(title, href, TvType.Movie) {
                this.posterUrl = posterUrl
            }
        }

        return HomePageResponse(arrayListOf(HomePageList("Latest Releases", home)))
    }

    // 2. Search Functionality
    override suspend fun search(query: String): List<SearchResponse> {
        val searchUrl = "$mainUrl/?q=$query"
        val document = app.get(searchUrl).document

        return document.select("div.container img.loaded, div.TPostMv, article").mapNotNull { element ->
            val parentA = element.selectFirst("a") ?: element.parent()?.selectFirst("a") ?: return@mapNotNull null
            val href = fixUrl(parentA.attr("href"))
            val img = element.selectFirst("img") ?: parentA.selectFirst("img") ?: return@mapNotNull null
            val title = img.attr("alt").takeIf { !it.isNullOrBlank() } ?: return@mapNotNull null
            val posterUrl = fixUrl(img.attr("src"))

            newMovieSearchResponse(title, href, TvType.Movie) {
                this.posterUrl = posterUrl
            }
        }
    }

    // 3. Detail Page Load (Movie / Series Info)
    override suspend fun load(url: String): LoadResponse {
        val document = app.get(url).document
        
        val title = document.selectFirst("h1, .entry-title, h1.Title")?.text()?.trim() ?: "No Title"
        val poster = fixUrl(document.selectFirst(".entry-content img, div.Image img, .post-thumbnail img")?.attr("src") ?: "")
        val description = document.selectFirst(".entry-content p, div.WP_ADC")?.text()?.trim()

        val isSeries = title.contains("Season", true) || title.contains("Episode", true)
        val tvType = if (isSeries) TvType.TvSeries else TvType.Movie

        return if (isSeries) {
            newTvSeriesLoadResponse(title, url, tvType, listOf()) {
                this.posterUrl = poster
                this.plot = description
            }
        } else {
            newMovieLoadResponse(title, url, tvType, url) {
                this.posterUrl = poster
                this.plot = description
            }
        }
    }

    // 4. Video Links & Download Extractors
    override suspend fun loadLinks(
        url: String,
        isDubbed: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val document = app.get(url).document

        document.select("a.external, .download-links a, .maxbutton a").forEach { element ->
            val link = element.attr("href")
            if (link.isNotBlank()) {
                loadExtractor(link, url, subtitleCallback, callback)
            }
        }

        return true
    }
}

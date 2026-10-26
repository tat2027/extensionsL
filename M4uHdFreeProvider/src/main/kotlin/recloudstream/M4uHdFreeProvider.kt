package recloudstream

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*

class M4uHdFreeProvider : MainAPI() {
    override var mainUrl = "https://m4uhdfree.net"
    override var name = "M4uHDFree"
    override var supportedTypes = setOf(TvType.Movie, TvType.TvSeries)
    override var lang = "en"
    override var hasMainPage = true

    override val mainPage = mainPageOf(
        "$mainUrl/" to "Home",
        "$mainUrl/movie" to "Movies",
        "$mainUrl/tv-show" to "TV Series"
    )

    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val document = app.get(request.data).document
        val home = document.select("div.item, div.film_list-wrap div.fl-item, .item-ael").mapNotNull { element ->
            val title = element.selectFirst("a.title, .name, h3 a")?.text() ?: return@mapNotNull null
            val href = element.selectFirst("a.title, .name, h3 a")?.attr("href") ?: return@mapNotNull null
            val poster = element.selectFirst("img")?.attr("data-src") ?: element.selectFirst("img")?.attr("src")
            
            val fixedHref = if (href.startsWith("http")) href else mainUrl + href
            val isTv = title.contains("Season") || element.text().contains("TV")

            if (isTv) {
                newTvSeriesSearchResponse(title, fixedHref, TvType.TvSeries) {
                    this.posterUrl = poster
                }
            } else {
                newMovieSearchResponse(title, fixedHref, TvType.Movie) {
                    this.posterUrl = poster
                }
            }
        }
        return newHomePageResponse(listOf(HomePageList(request.name, home)))
    }

    override suspend fun search(query: String): List<SearchResponse> {
        val url = "$mainUrl/search/$query.html"
        val document = app.get(url).document
        return document.select("div.item, div.fl-item").mapNotNull { element ->
            val title = element.selectFirst("a.title, .name, h3 a")?.text() ?: return@mapNotNull null
            val href = element.selectFirst("a.title, .name, h3 a")?.attr("href") ?: return@mapNotNull null
            val poster = element.selectFirst("img")?.attr("data-src") ?: element.selectFirst("img")?.attr("src")
            
            val fixedHref = if (href.startsWith("http")) href else mainUrl + href

            newMovieSearchResponse(title, fixedHref, TvType.Movie) {
                this.posterUrl = poster
            }
        }
    }

    override suspend fun load(url: String): LoadResponse {
        val document = app.get(url).document
        val title = document.selectFirst("h1, .movie-title")?.text().toString()
        val poster = document.selectFirst(".film-poster img, .mvi-cover img, img.img-responsive")?.attr("src")
        val description = document.selectFirst(".description, .film-description, .details")?.text()
        
        val episodes = document.select("div.les-content a, ul.episodes-list li a").mapNotNull { ep ->
            val epName = ep.text()
            val epHref = ep.attr("href")
            val fixedEpHref = if (epHref.startsWith("http")) epHref else mainUrl + epHref
            newEpisode(fixedEpHref) {
                name = epName
            }
        }

        return if (episodes.isNotEmpty()) {
            newTvSeriesLoadResponse(title, url, TvType.TvSeries, episodes) {
                this.posterUrl = poster
                this.plot = description
            }
        } else {
            newMovieLoadResponse(title, url, TvType.Movie, url) {
                this.posterUrl = poster
                this.plot = description
            }
        }
    }

    override suspend fun loadLinks(data: String, isCasting: Boolean, subtitleCallback: (SubtitleFile) -> Unit, callback: (ExtractorLink) -> Unit): Boolean {
        val document = app.get(data).document
        val videoUrl = document.selectFirst("iframe")?.attr("src") ?: data
        loadExtractor(videoUrl, data, subtitleCallback, callback)
        return true
    }
}

package cz.angelina.kotlingithub.infrastructure

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Query

internal interface GitHubService {

    @JsonClass(generateAdapter = true)
    data class SearchResponseDto(
        @Json(name = "items") val repos: List<RepoDto>
    )

    @JsonClass(generateAdapter = true)
    data class RepoDto(
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "description") val description: String
    )

    @GET("search/repositories")
    suspend fun searchRepos(
        @Query("q") query: String = DEFAULT_QUERY,
        @Query("sort") sortedBy: String = DEFAULT_SORTED_BY,
        @Query("order") orderedBy: String = DEFAULT_ORDERED_BY,
        @Query("per_page") itemsPerPage: Int = ITEMS_PER_PAGE
    ): SearchResponseDto

    companion object {
        const val DEFAULT_QUERY = "language:kotlin"
        const val DEFAULT_SORTED_BY = "stars"
        const val DEFAULT_ORDERED_BY = "desc"
        const val ITEMS_PER_PAGE = 10
    }
}

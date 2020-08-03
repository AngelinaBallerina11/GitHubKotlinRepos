package cz.angelina.kotlingithub.infrastructure

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.joda.time.DateTime
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
        @Json(name = "full_name") val fullRepoName: String,
        @Json(name = "description") val description: String?,
        @Json(name = "owner") val repoOwner: OwnerDto,
        @Json(name = "stargazers_count") val stars: Int,
        @Json(name = "watchers_count") val watchers: Int,
        @Json(name = "forks_count") val forks: Int,
        @Json(name = "created_at") val createdAt: DateTime,
        @Json(name = "updated_at") val updatedAt: DateTime
    )

    @JsonClass(generateAdapter = true)
    data class OwnerDto(
        @Json(name = "avatar_url") val avatarUrl: String
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
        const val ITEMS_PER_PAGE = 0
    }
}

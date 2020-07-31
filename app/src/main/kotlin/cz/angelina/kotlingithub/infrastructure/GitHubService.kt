package cz.angelina.kotlingithub.infrastructure

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.GET

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

    @GET("search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=1")
    suspend fun searchRepos(): SearchResponseDto
}

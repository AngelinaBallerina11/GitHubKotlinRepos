package cz.angelina.kotlingithub.infrastructure

import cz.angelina.kotlingithub.model.ErrorResult
import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import okhttp3.ResponseBody
import org.joda.time.DateTime
import retrofit2.HttpException
import retrofit2.Response

internal class GitHubSourceImplTest : StringSpec({
    val service = mockk<GitHubService>()
    val source = GitHubSourceImpl(service)

    "Source should return Success if service returns valid response" {
        coEvery { service.searchRepos() } returns GitHubService.SearchResponseDto(
            arrayListOf(
                GitHubService.RepoDto(
                    name = "repoName",
                    id = 1,
                    fullRepoName = "",
                    description = "description",
                    repoOwner = GitHubService.OwnerDto("url"),
                    stars = 1,
                    watchers = 2,
                    forks = 3,
                    size = 4,
                    createdAt = DateTime(),
                    updatedAt = DateTime()
                )
            )
        )

        source.getKotlinRepos().shouldBeInstanceOf<Result.Success<List<Repo>>>()

        coVerify { service.searchRepos() }
    }

    "Source should return Error if service returns an exception" {
        coEvery { service.searchRepos() } throws HttpException(
            Response.error<GitHubService.SearchResponseDto>(
                400,
                ResponseBody.create(null, "body")
            )
        )

        source.getKotlinRepos().shouldBeInstanceOf<Result.Error<ErrorResult>>()

        coVerify { service.searchRepos() }
    }
})

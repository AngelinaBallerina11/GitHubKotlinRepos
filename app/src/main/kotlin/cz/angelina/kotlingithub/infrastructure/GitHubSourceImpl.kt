package cz.angelina.kotlingithub.infrastructure

import cz.angelina.kotlingithub.data.GitHubSource
import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result

internal class GitHubSourceImpl(
    private val gitHubService: GitHubService
) : GitHubSource {
    override suspend fun getKotlinRepos(): Result<List<Repo>> = safeCall("Error fetching Kotlin repos") {
        gitHubService.searchRepos().repos.let {
            Result.Success(it.mapToBusinessEntity())
        }
    }

    private fun List<GitHubService.RepoDto>.mapToBusinessEntity(): List<Repo> = map {
        Repo(
            it.id,
            it.name,
            it.fullRepoName,
            it.description.orEmpty(),
            it.repoOwner.avatarUrl,
            it.stars,
            it.watchers,
            it.forks,
            it.size,
            it.createdAt,
            it.updatedAt
        )
    }
}

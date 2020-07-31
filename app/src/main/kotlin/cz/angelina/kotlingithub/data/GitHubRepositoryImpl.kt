package cz.angelina.kotlingithub.data

import cz.angelina.kotlingithub.domain.GitHubRepository
import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result

internal class GitHubRepositoryImpl(
    private val gitHubSource: GitHubSource
) : GitHubRepository {
    override suspend fun getKotlinRepos(): Result<List<Repo>> {
        return gitHubSource.getKotlinRepos()
    }
}

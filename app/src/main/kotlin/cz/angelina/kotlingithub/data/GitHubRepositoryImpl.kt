package cz.angelina.kotlingithub.data

import cz.angelina.kotlingithub.domain.GitHubRepository
import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result

/**
 * Simple repository implementation which just calls the `infrastructure` layer
 * using the `source` interface.
 * If the app had a DB or cache the caching logic (e.g. fetching the data from the remote
 * API and then saving it in the DB, cache expiration times, etc.) would be implemented here.
 * In this implementation the repository does not store the data, but only passes it further
 * to the `domain` layer.
 * @property gitHubSource source of data
 */
internal class GitHubRepositoryImpl(
    private val gitHubSource: GitHubSource
) : GitHubRepository {
    override suspend fun getKotlinRepos(): Result<List<Repo>> {
        return gitHubSource.getKotlinRepos()
    }
}

package cz.angelina.kotlingithub.domain

import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result

/**
 * Interface exposed by the `domain` layer and it will be implemented in the `data` layer.
 */
internal interface GitHubRepository {
    suspend fun getKotlinRepos(): Result<List<Repo>>
}

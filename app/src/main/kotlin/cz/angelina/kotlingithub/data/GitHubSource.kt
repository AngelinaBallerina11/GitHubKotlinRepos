package cz.angelina.kotlingithub.data

import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result

/**
 * `Source` interface exposed by the `data` layer, so that the concrete
 * implementation can be done by the `infrastructure` layer.
 */
internal interface GitHubSource {
    suspend fun getKotlinRepos(): Result<List<Repo>>
}

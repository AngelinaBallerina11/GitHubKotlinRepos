package cz.angelina.kotlingithub.data

import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result

internal interface GitHubSource {
    suspend fun getKotlinRepos(): Result<List<Repo>>
}

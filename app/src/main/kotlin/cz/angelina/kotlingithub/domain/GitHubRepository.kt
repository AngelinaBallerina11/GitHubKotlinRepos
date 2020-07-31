package cz.angelina.kotlingithub.domain

import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result

internal interface GitHubRepository {
    suspend fun getKotlinRepos(): Result<List<Repo>>
}

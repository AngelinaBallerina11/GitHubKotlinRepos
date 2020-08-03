package cz.angelina.kotlingithub.domain

import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetTrendingKotlinReposUseCase(
    private val gitHubRepo: GitHubRepository
) : SuspendUseCase<Unit, Result<List<Repo>>> {
    override suspend fun invoke(input: Unit): Result<List<Repo>> {
        return withContext(Dispatchers.IO) { gitHubRepo.getKotlinRepos() }
    }
}

package cz.angelina.kotlingithub.domain

import io.kotest.core.spec.style.StringSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class GetTrendingKotlinReposUseCaseTest : StringSpec({

    val repo = mockk<GitHubRepository>()
    val useCase = GetTrendingKotlinReposUseCase(repo)

    "UseCase invocation should call repository method" {
        coEvery { repo.getKotlinRepos() } returns mockk()

        useCase()

        coVerify { repo.getKotlinRepos() }
    }
})

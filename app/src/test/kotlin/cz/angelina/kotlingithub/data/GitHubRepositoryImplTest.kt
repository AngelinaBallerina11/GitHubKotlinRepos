package cz.angelina.kotlingithub.data

import io.kotest.core.spec.style.StringSpec
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

internal class GitHubRepositoryImplTest : StringSpec({
    val source = mockk<GitHubSource>()
    val repository = GitHubRepositoryImpl(source)

    "when getKotlinRepos() is called in repository the source fetches the data" {
        coEvery { source.getKotlinRepos() } returns mockk()

        repository.getKotlinRepos()

        coVerify { source.getKotlinRepos() }
    }
})

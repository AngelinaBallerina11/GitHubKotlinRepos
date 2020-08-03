package cz.angelina.kotlingithub.presentation

import cz.angelina.kotlingithub.domain.GetTrendingKotlinReposUseCase
import cz.angelina.kotlingithub.model.ErrorResult
import cz.angelina.kotlingithub.model.Result
import cz.angelina.kotlingithub.runCollectingSuspend
import cz.angelina.kotlingithub.system.emptyRepo
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldEndWith
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
internal class ListViewModelTest : StringSpec({

    val getReposUseCase = mockk<GetTrendingKotlinReposUseCase>()
    val viewModel = ListViewModel(getReposUseCase)

    "initial state should be Loading" {
        runCollectingSuspend(viewModel.viewState) { } shouldEndWith ListViewModel.State.Loading
    }

    "UseCase returns Successful result with EMPTY list" {
        coEvery { getReposUseCase(Unit) } returns Result.Success(listOf())
        runCollectingSuspend(viewModel.viewState) {
            viewModel.fetchKotlinRepos()
        } shouldEndWith ListViewModel.State.Empty
    }

    "UseCase returns Successful result with non-empty list" {
        coEvery { getReposUseCase(Unit) } returns Result.Success(listOf(emptyRepo()))
        runCollectingSuspend(viewModel.viewState) {
            viewModel.fetchKotlinRepos()
        } shouldEndWith ListViewModel.State.Loaded(listOf(emptyRepo()))
    }

    "UseCase returns Error with UnknownHostException" {
        val exception = UnknownHostException()
        coEvery { getReposUseCase(Unit) } returns
            Result.Error(ErrorResult("123 error", exception))
        val flow = runCollectingSuspend(viewModel.viewState) {
            viewModel.fetchKotlinRepos()
        }
        flow shouldEndWith ListViewModel.State.Error(cause = exception)
        (flow.last() as ListViewModel.State.Error).errorMessage shouldBe cz.angelina.kotlingithub.R.string.no_internet_error
    }

    "UseCase returns a random Error" {
        val exception = RuntimeException()
        coEvery { getReposUseCase(Unit) } returns
            Result.Error(ErrorResult("123 error", exception))
        val flow = runCollectingSuspend(viewModel.viewState) {
            viewModel.fetchKotlinRepos()
        }
        flow shouldEndWith ListViewModel.State.Error(cause = exception)
        (flow.last() as ListViewModel.State.Error).errorMessage shouldBe cz.angelina.kotlingithub.R.string.general_error
    }
})

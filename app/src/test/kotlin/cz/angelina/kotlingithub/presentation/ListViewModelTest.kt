package cz.angelina.kotlingithub.presentation

import cz.angelina.kotlingithub.domain.GetTrendingKotlinReposUseCase
import cz.angelina.kotlingithub.model.ErrorResult
import cz.angelina.kotlingithub.model.Result
import cz.angelina.kotlingithub.runCollectingLaunch
import cz.angelina.kotlingithub.runCollectingSuspend
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldEndWith
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import java.net.UnknownHostException
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
internal class ListViewModelTest : StringSpec({

    val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    beforeTest {
        Dispatchers.setMain(mainThreadSurrogate)
    }
    afterTest { (_, _) ->
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    "initial state should be Loading" {
        val getReposUseCase = mockk<GetTrendingKotlinReposUseCase>(relaxed = true)
        val viewModel = ListViewModel(getReposUseCase)
        runCollectingSuspend(viewModel.viewState) { } shouldEndWith ListViewModel.State.Loading
    }

    "UseCase returns Successful result with EMPTY list" {
        runBlockingTest {
            val getReposUseCase = mockk<GetTrendingKotlinReposUseCase>(relaxed = true)
            coEvery { getReposUseCase(Unit) } returns Result.Success(listOf())
            val viewModel = ListViewModel(getReposUseCase)
            runCollectingLaunch(viewModel.viewState) shouldEndWith ListViewModel.State.Empty
        }
    }

    "UseCase returns Successful result with non-empty list" {
        runBlockingTest {
            val getReposUseCase = mockk<GetTrendingKotlinReposUseCase>(relaxed = true)
            coEvery { getReposUseCase(Unit) } returns Result.Success(listOf(emptyRepo()))
            val viewModel = ListViewModel(getReposUseCase)
            runCollectingLaunch(viewModel.viewState) shouldEndWith ListViewModel.State.Loaded(listOf(emptyRepo()))
        }
    }

    "UseCase returns Error with UnknownHostException" {
        runBlockingTest {
            val exception = UnknownHostException()
            val getReposUseCase = mockk<GetTrendingKotlinReposUseCase>(relaxed = true)
            coEvery { getReposUseCase(Unit) } returns
                Result.Error(ErrorResult("123 error", exception))
            val viewModel = ListViewModel(getReposUseCase)
            val flow = runCollectingLaunch(viewModel.viewState)
            flow shouldEndWith ListViewModel.State.Error(cause = exception)
            (flow.last() as ListViewModel.State.Error).errorMessage shouldBe cz.angelina.kotlingithub.R.string.no_internet_error
        }
    }

    "UseCase returns a random Error" {
        runBlockingTest {
            val exception = RuntimeException()
            val getReposUseCase = mockk<GetTrendingKotlinReposUseCase>()
            coEvery { getReposUseCase(Unit) } returns
                Result.Error(ErrorResult("123 error", exception))
            val viewModel = ListViewModel(getReposUseCase)
            val flow = runCollectingLaunch(viewModel.viewState)
            flow shouldEndWith ListViewModel.State.Error(cause = exception)
            (flow.last() as ListViewModel.State.Error).errorMessage shouldBe cz.angelina.kotlingithub.R.string.general_error
        }
    }
})

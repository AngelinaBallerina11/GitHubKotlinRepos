package cz.angelina.kotlingithub.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import cz.angelina.kotlingithub.domain.GetTrendingKotlinReposUseCase
import cz.angelina.kotlingithub.domain.invoke
import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

@ExperimentalCoroutinesApi
internal class ListViewModel(private val getKotlinRepos: GetTrendingKotlinReposUseCase) : ViewModel() {

    private val _viewState = MutableStateFlow<State<List<Repo>>>(State.Loading)
    val viewState: StateFlow<State<List<Repo>>> = _viewState

    suspend fun fetchKotlinRepos() {
        when (val result = getKotlinRepos()) {
            is Result.Success -> {
                Timber.d("SUCCESS ${result.data}")
                _viewState.value = if (result.data.isEmpty()) State.Empty else State.Loaded(result.data)
            }
            is Result.Error -> {
                Timber.d("ERROR ${result.error.message} \n ${result.error.throwable}")
                _viewState.value = State.Error(result.error.throwable ?: RuntimeException())
            }
        }
    }

    internal sealed class State<out T> {
        object Loading : State<Nothing>() {
            override fun toString() = "Loading"
        }

        object Empty : State<Nothing>() {
            override fun toString() = "Empty"
        }

        data class Loaded<out T>(val value: T) : State<T>() {
            override fun toString() = value.toString()
        }

        data class Error(
            val cause: Throwable
        ) : State<Nothing>() {
            @StringRes
            val errorMessage: Int = when (cause) {
                is java.net.UnknownHostException -> cz.angelina.kotlingithub.R.string.no_internet_error
                else -> cz.angelina.kotlingithub.R.string.general_error
            }
        }
    }
}

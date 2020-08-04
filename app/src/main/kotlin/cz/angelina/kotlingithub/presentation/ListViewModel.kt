package cz.angelina.kotlingithub.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.angelina.kotlingithub.domain.GetTrendingKotlinReposUseCase
import cz.angelina.kotlingithub.domain.invoke
import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * ViewModel which gets the list of GitHub repositories
 * It has the Activity scope so that the fetching is not
 * done each time the Fragment is created.
 * @property getKotlinRepos UseCase to fetch Kotlin repositories
 */
@ExperimentalCoroutinesApi
internal class ListViewModel(private val getKotlinRepos: GetTrendingKotlinReposUseCase) : ViewModel() {

    private val _viewState = MutableStateFlow<State<List<Repo>>>(State.Loading)
    val viewState: StateFlow<State<List<Repo>>> = _viewState

    /**
     * Call the GitHub API as soon as the ViewModel is created
     */
    init {
        viewModelScope.launch { fetchKotlinRepos() }
    }

    /**
     * Fetching the list of Kotlin repositories and updating the view's state with the result
     */
    private suspend fun fetchKotlinRepos() {
        when (val result = getKotlinRepos()) {
            is Result.Success -> {
                Timber.d("SUCCESS ${result.data}")
                _viewState.value = if (result.data.isEmpty()) State.Empty else State.Loaded(result.data)
            }
            is Result.Error -> {
                Timber.d("ERROR ${result.error.message} \n${result.error.throwable}")
                _viewState.value = State.Error(result.error.throwable ?: RuntimeException())
            }
        }
    }

    /**
     * Screen state used for loading the data
     */
    internal sealed class State<out T> {
        /**
         * Screen is loading
         */
        object Loading : State<Nothing>() {
            override fun toString() = "Loading"
        }

        /**
         * The call was successful but there are no Kotlin repositories
         */
        object Empty : State<Nothing>() {
            override fun toString() = "Empty"
        }

        /**
         * The call was successful and it contains a list of Kotlin repositories
         * @property value loaded values, here Kotlin repositories
         */
        data class Loaded<out T>(val value: T) : State<T>() {
            override fun toString() = value.toString()
        }

        /**
         * Backend error occurred
         * @property cause exception thrown by the infrastructure layer
         * @property errorMessage resource ID of the error message which would be displayed on the screen
         */
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

package cz.angelina.kotlingithub.presentation

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

@ExperimentalCoroutinesApi
internal class MainViewModel(private val getKotlinRepos: GetTrendingKotlinReposUseCase) : ViewModel() {

    private val _kotlinRepos = MutableStateFlow<List<Repo>>(emptyList())
    val kotlinRepos: StateFlow<List<Repo>> = _kotlinRepos

    init {
        viewModelScope.launch { load() }
    }

    private suspend fun load() {
        when (val result = getKotlinRepos()) {
            is Result.Success -> {
                Timber.d("ANGELINA111 SUCCESS ${result.data}")
                _kotlinRepos.value = result.data
            }
            is Result.Error -> {
                Timber.d("ANGELINA111 ERROR ${result.error.message} \n ${result.error.throwable}")
            }
        }
    }
}

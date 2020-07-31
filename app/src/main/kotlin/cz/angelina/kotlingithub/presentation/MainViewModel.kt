package cz.angelina.kotlingithub.presentation

import androidx.lifecycle.ViewModel
import cz.angelina.kotlingithub.domain.GetTrendingKotlinReposUseCase
import cz.angelina.kotlingithub.domain.invoke
import cz.angelina.kotlingithub.model.Result
import timber.log.Timber

internal class MainViewModel(private val getKotlinRepos: GetTrendingKotlinReposUseCase) : ViewModel() {

    suspend fun load() {
        when (val result = getKotlinRepos()) {
            is Result.Success -> {
                Timber.d("ANGELINA111 SUCCESS ${result.data}")
            }
            is Result.Error -> {
                Timber.d("ANGELINA111 ERROR ${result.error.message} \n ${result.error.throwable}")
            }
        }
    }
}

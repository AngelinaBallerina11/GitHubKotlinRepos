package cz.angelina.kotlingithub.di

import com.squareup.moshi.Moshi
import cz.angelina.kotlingithub.data.GitHubRepositoryImpl
import cz.angelina.kotlingithub.data.GitHubSource
import cz.angelina.kotlingithub.domain.GetTrendingKotlinReposUseCase
import cz.angelina.kotlingithub.domain.GitHubRepository
import cz.angelina.kotlingithub.infrastructure.GitHubService
import cz.angelina.kotlingithub.infrastructure.GitHubSourceImpl
import cz.angelina.kotlingithub.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val mainModule = module {

    viewModel { MainViewModel(get()) }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .build()
    }
    single { get<Retrofit>().create(GitHubService::class.java) }

    factory { GetTrendingKotlinReposUseCase(get()) }

    factory<GitHubSource> { GitHubSourceImpl(get()) }

    factory<GitHubRepository> { GitHubRepositoryImpl(get()) }
}

package cz.angelina.kotlingithub.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import cz.angelina.kotlingithub.data.GitHubRepositoryImpl
import cz.angelina.kotlingithub.data.GitHubSource
import cz.angelina.kotlingithub.domain.GetTrendingKotlinReposUseCase
import cz.angelina.kotlingithub.domain.GitHubRepository
import cz.angelina.kotlingithub.infrastructure.GitHubService
import cz.angelina.kotlingithub.infrastructure.GitHubSourceImpl
import cz.angelina.kotlingithub.presentation.DetailViewModel
import cz.angelina.kotlingithub.presentation.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
val mainModule = module {

    viewModel { ListViewModel(get()) }
    viewModel { DetailViewModel() }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(DateTimeAdapter())
                        .build()
                )
            )
            .build()
    }
    single { get<Retrofit>().create(GitHubService::class.java) }

    factory { GetTrendingKotlinReposUseCase(get()) }

    factory<GitHubSource> { GitHubSourceImpl(get()) }

    factory<GitHubRepository> { GitHubRepositoryImpl(get()) }
}

private class DateTimeAdapter {

    val format = "yyyy-MM-dd'T'HH:mm:ssZ"
    val formatter: DateTimeFormatter = DateTimeFormat.forPattern(format)

    @ToJson
    fun toJson(dateTime: DateTime?): String {
        return formatter.print(dateTime)
    }

    @FromJson
    fun fromJson(string: String?): DateTime {
        return DateTime.parse(string, formatter)
    }
}

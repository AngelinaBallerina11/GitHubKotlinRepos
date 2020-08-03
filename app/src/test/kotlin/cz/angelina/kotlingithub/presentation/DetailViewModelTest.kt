package cz.angelina.kotlingithub.presentation

import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.runCollectingSuspend
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldEndWith
import io.kotest.matchers.collections.shouldNotEndWith
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.joda.time.DateTime

@ExperimentalCoroutinesApi
internal class DetailViewModelTest : StringSpec({

    val viewModel = DetailViewModel()
    val testDateTime = DateTime(2020, 1, 1, 1, 1, 1)

    "when no repo is passed to the ViewModel the View gets empty ViewState" {
        runCollectingSuspend(viewModel.viewState) {} shouldEndWith DetailViewModel.ViewState()
    }

    "name is not taken from the name parameter" {
        forAll(
            row(emptyRepo().copy(name = "1")),
            row(emptyRepo().copy(name = "ABC")),
            row(emptyRepo().copy(name = "@#$%"))
        ) { repo ->
            runCollectingSuspend(viewModel.viewState) {
                viewModel.setRepoDetails(repo, testDateTime)
            } shouldNotEndWith emptyViewState().copy(name = repo.name)
        }
    }

    "name is taken from the full name parameter" {
        forAll(
            row(emptyRepo()),
            row(emptyRepo().copy(fullRepoName = "1")),
            row(emptyRepo().copy(fullRepoName = "ABC")),
            row(emptyRepo().copy(fullRepoName = "@#$%"))
        ) { repo ->
            runCollectingSuspend(viewModel.viewState) {
                viewModel.setRepoDetails(repo, testDateTime)
            } shouldEndWith emptyViewState().copy(name = repo.fullRepoName)
        }
    }

    "description is taken from the description parameter" {
        forAll(
            row(emptyRepo()),
            row(emptyRepo().copy(description = "1")),
            row(emptyRepo().copy(description = "ABC")),
            row(emptyRepo().copy(description = "@#$%"))
        ) { repo ->
            runCollectingSuspend(viewModel.viewState) {
                viewModel.setRepoDetails(repo, testDateTime)
            } shouldEndWith emptyViewState().copy(description = repo.description)
        }
    }

    "watchers count is formatted correctly" {
        forAll(
            row(emptyRepo().copy(watchers = 1_111), "1k"),
            row(emptyRepo().copy(watchers = 1_551), "1k"),
            row(emptyRepo().copy(watchers = 1_911), "1k"),
            row(emptyRepo().copy(watchers = 911), "911"),
            row(emptyRepo().copy(watchers = 3_456_123), "3456k"),
            row(emptyRepo().copy(watchers = 1), "1")
        ) { repo, result ->
            runCollectingSuspend(viewModel.viewState) {
                viewModel.setRepoDetails(repo, testDateTime)
            } shouldEndWith emptyViewState().copy(watchersCount = result)
        }
    }

    "forks count is formatted correctly" {
        forAll(
            row(emptyRepo().copy(forks = 1_111), "1k"),
            row(emptyRepo().copy(forks = 1_551), "1k"),
            row(emptyRepo().copy(forks = 1_911), "1k"),
            row(emptyRepo().copy(forks = 911), "911"),
            row(emptyRepo().copy(forks = 3_456_123), "3456k"),
            row(emptyRepo().copy(forks = 1), "1")
        ) { repo, result ->
            runCollectingSuspend(viewModel.viewState) {
                viewModel.setRepoDetails(repo, testDateTime)
            } shouldEndWith emptyViewState().copy(forksCount = result)
        }
    }

    "stars count is formatted correctly" {
        forAll(
            row(emptyRepo().copy(stars = 1_111), "1k"),
            row(emptyRepo().copy(stars = 1_551), "1k"),
            row(emptyRepo().copy(stars = 1_911), "1k"),
            row(emptyRepo().copy(stars = 911), "911"),
            row(emptyRepo().copy(stars = 3_456_123), "3456k"),
            row(emptyRepo().copy(stars = 1), "1")
        ) { repo, result ->
            runCollectingSuspend(viewModel.viewState) {
                viewModel.setRepoDetails(repo, testDateTime)
            } shouldEndWith emptyViewState().copy(starsCount = result)
        }
    }

    "time interval from the created time is calculated correctly" {
        forAll(
            row(
                emptyRepo().copy(
                    createdAt = DateTime(2020, 1, 1, 0, 0, 0),
                    updatedAt = DateTime(2020, 1, 1, 0, 0, 0)
                ),
                DateTime(2021, 1, 1, 0, 0, 0),
                DetailViewModel.TimePeriod(1, 0, 0, 0, 0, 0)
            ),
            row(
                emptyRepo().copy(
                    createdAt = DateTime(2020, 1, 1, 0, 0, 0),
                    updatedAt = DateTime(2020, 1, 1, 0, 0, 0)
                ),
                DateTime(2221, 1, 1, 0, 0, 0),
                DetailViewModel.TimePeriod(201, 0, 0, 0, 0, 0)
            ),
            row(
                emptyRepo().copy(
                    createdAt = DateTime(2020, 10, 1, 0, 0, 0),
                    updatedAt = DateTime(2020, 10, 1, 0, 0, 0)
                ),
                DateTime(2221, 1, 1, 0, 0, 0),
                DetailViewModel.TimePeriod(200, 3, 0, 0, 0, 0)
            )
        ) { repo, testNowTime, result ->
            runCollectingSuspend(viewModel.viewState) {
                viewModel.setRepoDetails(repo, testNowTime)
            } shouldEndWith emptyViewState()
                .copy(timePeriodSinceCreation = result, timePeriodSinceUpdate = result)
        }
    }
})

fun emptyRepo() = Repo(
    id = 0,
    name = "",
    fullRepoName = "",
    description = "",
    avatarUrl = "",
    stars = 0,
    watchers = 0,
    forks = 0,
    createdAt = DateTime(2020, 1, 1, 1, 1, 1),
    updatedAt = DateTime(2020, 1, 1, 1, 1, 1)
)

@ExperimentalCoroutinesApi
internal fun emptyViewState() = DetailViewModel.ViewState(
    name = "",
    description = "",
    watchersCount = "0",
    starsCount = "0",
    forksCount = "0",
    timePeriodSinceCreation = DetailViewModel.TimePeriod(),
    timePeriodSinceUpdate = DetailViewModel.TimePeriod()
)

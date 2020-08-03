package cz.angelina.kotlingithub.presentation

import androidx.lifecycle.ViewModel
import cz.angelina.kotlingithub.model.Repo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Period

@ExperimentalCoroutinesApi
internal class DetailViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState

    fun setRepoDetails(repo: Repo, now: DateTime = DateTime(DateTimeZone.UTC)) {
        _viewState.value = _viewState.value.copy(
            name = repo.fullRepoName,
            description = repo.description,
            watchersCount = repo.watchers.formatToKilo(),
            forksCount = repo.forks.formatToKilo(),
            starsCount = repo.stars.formatToKilo(),
            timePeriodSinceCreation = repo.createdAt.formatToPeriod(now),
            timePeriodSinceUpdate = repo.updatedAt.formatToPeriod(now)
        )
    }

    data class ViewState(
        val name: String = "",
        val description: String = "",
        val watchersCount: String = "",
        val forksCount: String = "",
        val starsCount: String = "",
        val timePeriodSinceCreation: TimePeriod = TimePeriod(),
        val timePeriodSinceUpdate: TimePeriod = TimePeriod()
    )

    data class TimePeriod(
        val years: Int = 0,
        val months: Int = 0,
        val days: Int = 0,
        val hours: Int = 0,
        val minutes: Int = 0,
        val seconds: Int = 0
    )

    private fun Int.formatToKilo(): String = if (this > 1_000) "${this / 1_000}k" else this.toString()

    private fun DateTime.formatToPeriod(now: DateTime): TimePeriod {
        val period = Period(this, now)
        return TimePeriod(period.years, period.months, period.days, period.hours, period.minutes, period.seconds)
    }
}

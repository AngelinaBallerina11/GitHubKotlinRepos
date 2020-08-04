package cz.angelina.kotlingithub.presentation

import androidx.lifecycle.ViewModel
import cz.angelina.kotlingithub.model.Repo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Period

/**
 * ViewModel to process the [Repo] object which came from the [ListViewModel].
 * This ViewModel has Fragment scope as we do not fetch any data and we only
 * provide formatted data for the view.
 */
@ExperimentalCoroutinesApi
internal class DetailViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = _viewState

    /**
     * Used by the View to pass the [Repo] object to the ViewModel and update its state [ViewState]
     */
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

    /**
     * A simple implementation of the screen state. Here we do not need to load
     * anything or display errors and were only perform data formatting for the screen.
     * @property name full repository name, which will be displayed as the title of the page
     * @property description repository description
     * @property watchersCount watchers count
     * @property forksCount forks count
     * @property starsCount stars count
     * @property timePeriodSinceCreation period of time since the current moment
     * @property timePeriodSinceUpdate period of time since the current moment
     */
    data class ViewState(
        val name: String = "",
        val description: String = "",
        val watchersCount: String = "",
        val forksCount: String = "",
        val starsCount: String = "",
        val timePeriodSinceCreation: TimePeriod = TimePeriod(),
        val timePeriodSinceUpdate: TimePeriod = TimePeriod()
    )

    /**
     * Wrapper object which can be easily used by the View to display time period.
     * It is very similar to the [org.joda.time.Period] but it is easier to test and pass through the classes.
     */
    data class TimePeriod(
        val years: Int = 0,
        val months: Int = 0,
        val days: Int = 0,
        val hours: Int = 0,
        val minutes: Int = 0,
        val seconds: Int = 0
    )

    /**
     * Number of watcher/stars/forks formatting
     * `k` can be localized in `strings.xml` but depends on the business requirements
     * Used here as on GitHub for simplicity
     */
    private fun Int.formatToKilo(): String = if (this > 1_000) "${this / 1_000}k" else this.toString()

    private fun DateTime.formatToPeriod(now: DateTime): TimePeriod {
        val period = Period(this, now)
        return TimePeriod(period.years, period.months, period.days, period.hours, period.minutes, period.seconds)
    }
}

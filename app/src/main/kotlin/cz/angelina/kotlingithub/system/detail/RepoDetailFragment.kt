package cz.angelina.kotlingithub.system.detail

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import cz.angelina.kotlingithub.R
import cz.angelina.kotlingithub.presentation.DetailViewModel
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Displays the repository details
 */
@ExperimentalCoroutinesApi
class RepoDetailFragment : Fragment(R.layout.detail_fragment) {

    private val args by navArgs<RepoDetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setRepoDetails(args.selectedRepo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                updateUi(state)
            }
        }
    }

    private fun updateUi(viewState: DetailViewModel.ViewState) {
        tvName.text = viewState.name
        tvDescription.text = viewState.description
        tvWatchersCount.text = viewState.watchersCount
        tvForkCount.text = viewState.forksCount
        tvStarCount.text = viewState.starsCount
        tvCreated.text = formatTimePeriodString(R.string.created_time_ago, viewState.timePeriodSinceCreation)
        tvUpdated.text = formatTimePeriodString(R.string.updated_time_ago, viewState.timePeriodSinceUpdate)
    }

    private fun formatTimePeriodString(@StringRes stringResource: Int, period: DetailViewModel.TimePeriod): SpannableString {
        var omitDetails = false
        return context?.let { ctx ->
            val builder = StringBuilder().apply {
                if (period.years > 0) {
                    omitDetails = true
                    appendPlural(R.plurals.year, period.years)
                }
                appendPlural(R.plurals.month, period.months)
                if (!omitDetails) {
                    appendPlural(R.plurals.days, period.days)
                    appendPlural(R.plurals.hour, period.hours)
                    appendPlural(R.plurals.minute, period.minutes)
                    appendPlural(R.plurals.seconds, period.seconds)
                }
            }
            val finalString = getString(stringResource, builder.toString().trim())
            SpannableString(finalString).apply {
                setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(ctx, R.color.colorPrimary)),
                    0, this.indexOf(" "),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        } ?: SpannableString("")
    }

    private fun StringBuilder.appendPlural(@PluralsRes pluralRes: Int, quantity: Int) {
        if (quantity > 0) append(resources.getQuantityString(pluralRes, quantity, quantity)).append(" ")
    }
}

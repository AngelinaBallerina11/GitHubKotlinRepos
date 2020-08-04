package cz.angelina.kotlingithub.system.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.angelina.kotlingithub.R
import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.presentation.ListViewModel
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Displays the list of the top Kotlin repositories. For simplicity and due to
 * the demo scope only the first 10 values are displayed.
 */
@ExperimentalCoroutinesApi
class RepoListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel: ListViewModel by sharedViewModel()
    private val itemClickListener: (Repo) -> Unit = { repo ->
        findNavController().navigate(
            RepoListFragmentDirections.actionRepoListFragmentToRepoDetailFragment(
                repo
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rvRepositories) {
            adapter = RepoAdapter(itemClickListener)
            layoutManager = LinearLayoutManager(activity)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect {
                updateUi(it)
            }
        }
    }

    private fun updateUi(state: ListViewModel.State<List<Repo>>) {
        if (state is ListViewModel.State.Error) {
            showDialog(state)
        }
        if (state is ListViewModel.State.Loaded) {
            (rvRepositories.adapter as RepoAdapter).setRepos(state.value)
        }
        rvRepositories.isVisible = state is ListViewModel.State.Loaded
        tvEmptyList.isVisible = state is ListViewModel.State.Empty
        pbLoading.isVisible = state is ListViewModel.State.Loading
    }

    private fun showDialog(state: ListViewModel.State.Error) {
        activity?.let {
            AlertDialog.Builder(it)
        }?.apply {
            setCancelable(false)
            setMessage(getString(state.errorMessage, state.cause.localizedMessage))
            setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
                activity?.finish()
            }
        }?.create()?.show()
    }
}

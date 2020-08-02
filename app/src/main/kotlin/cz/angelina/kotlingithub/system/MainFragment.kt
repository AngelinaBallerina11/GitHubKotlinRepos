package cz.angelina.kotlingithub.system

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cz.angelina.kotlingithub.R
import cz.angelina.kotlingithub.model.Repo
import cz.angelina.kotlingithub.presentation.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rvRepositories) {
            adapter = RepoAdapter()
            layoutManager = LinearLayoutManager(activity)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect {
                updateUi(it)
            }
        }
    }

    private fun updateUi(state: MainViewModel.State<List<Repo>>) {
        if (state is MainViewModel.State.Error) {
            showDialog(state.errorMessage)
        }
        if (state is MainViewModel.State.Loaded) {
            (rvRepositories.adapter as RepoAdapter).setRepos(state.value)
        }
        rvRepositories.isVisible = state is MainViewModel.State.Loaded
        tvEmptyList.isVisible = state is MainViewModel.State.Empty
        pbLoading.isVisible = state is MainViewModel.State.Loading
    }

    private fun showDialog(@StringRes errorMessage: Int) {
        activity?.let {
            AlertDialog.Builder(it)
        }?.apply {
            setCancelable(false)
            setMessage(errorMessage)
            setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
                activity?.finish()
            }
        }?.create()?.show()
    }
}

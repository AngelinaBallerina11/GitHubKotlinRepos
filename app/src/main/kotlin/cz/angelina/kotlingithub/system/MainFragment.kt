package cz.angelina.kotlingithub.system

import androidx.fragment.app.Fragment
import cz.angelina.kotlingithub.R
import cz.angelina.kotlingithub.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModel()
}

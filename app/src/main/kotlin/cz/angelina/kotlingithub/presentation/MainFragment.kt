package cz.angelina.kotlingithub.presentation

import androidx.fragment.app.Fragment
import cz.angelina.kotlingithub.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModel()
}

package cz.angelina.kotlingithub.system

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import cz.angelina.kotlingithub.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * This app follows the `single-activity` pattern. This is the main host activity
 * for the fragment flow defined in the Jetpack Navigation Component
 */
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(R.layout.main_activity) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

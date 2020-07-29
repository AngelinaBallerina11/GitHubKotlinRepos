package cz.angelina.kotlingithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.angelina.kotlingithub.ui.main.MainFragment

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, MainFragment())
                .commitNow()
        }
    }
}

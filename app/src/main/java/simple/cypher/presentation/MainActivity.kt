package simple.cypher.presentation

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import simple.cypher.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme()
        setStatusBar()
        setLayout()
    }

    fun setTheme() = setTheme(
        when (isNightModeEnabled()) {
            true -> R.style.DarkTheme
            else -> R.style.LightTheme
        }
    )

    fun setStatusBar() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (!isNightModeEnabled())
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            //window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    fun setLayout() = setContentView(R.layout.main_activity)

    fun isNightModeEnabled() =
        resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

}
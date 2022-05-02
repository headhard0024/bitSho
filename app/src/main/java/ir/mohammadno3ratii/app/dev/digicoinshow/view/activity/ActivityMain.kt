package ir.mohammadno3ratii.app.dev.digicoinshow.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.mohammadno3ratii.app.dev.digicoinshow.R
import ir.mohammadno3ratii.app.dev.digicoinshow.base.PublicConstant
import ir.mohammadno3ratii.app.dev.digicoinshow.base.XmlParser
import ir.mohammadno3ratii.app.dev.digicoinshow.di.AppComponent
import ir.mohammadno3ratii.app.dev.digicoinshow.di.DaggerAppComponent
import ir.mohammadno3ratii.app.dev.digicoinshow.model.response.ReadCurrencyXml
import setupWithNavController
import javax.inject.Inject

class ActivityMain : AppCompatActivity() {

    lateinit var component:AppComponent

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize(savedInstanceState)

    }

    private fun initialize(savedInstanceState: Bundle?) {

        component = DaggerAppComponent.factory().create(this)
        component.ACTIVITY_MAIN(this)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navGraphIds = listOf(
            R.navigation.home_navigation,
            R.navigation.exchange_rate_navigation,
            R.navigation.setting_navigation
        )
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )
        currentNavController = controller
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onBackPressed() {
        if (currentNavController?.value?.popBackStack() == false) {
            super.onBackPressed()
        }
    }
}
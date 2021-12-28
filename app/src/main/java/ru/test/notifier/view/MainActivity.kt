package ru.test.notifier.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import ru.test.notifier.R
import androidx.core.view.GravityCompat
import android.view.View
import android.widget.Toast

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.test.notifier.NotifierApplication
import ru.test.notifier.navigation.Router
import ru.test.notifier.presenter.MainPresenter
import ru.test.notifier.view.dialogs.EventDialog


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var router: Router = NotifierApplication.getInstance().getRouter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutInflater.inflate(R.layout.activity_main, null, false)
        MainPresenter(view)
        setContentView(view)

        val drawer = findViewById<View>(R.id.main_layout) as DrawerLayout
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.navigation_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            router.showPage(this, Router.MAIN_PAGE)
        }

    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.main_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val page = when (item.itemId) {
            R.id.menu_main -> Router.MAIN_PAGE
            R.id.menu_profile -> Router.PROFILE_PAGE
            R.id.menu_events -> Router.EVENTS_PAGE
            R.id.menu_persons -> Router.PERSONS_PAGE
            R.id.menu_notification, R.id.menu_animation -> Router.SETTINGS_PAGE
            else -> Router.MAIN_PAGE
        }
        router.showPage(this, page)

        val drawer = findViewById<View>(R.id.main_layout) as DrawerLayout
            drawer.closeDrawer(GravityCompat.START)
            return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
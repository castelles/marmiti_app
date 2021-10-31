package castelles.com.github.androidbaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onResume() {
        super.onResume()
        setNavController()
        drawerLayout = setAppBarConfiguration()
        setNavigationView(drawerLayout)
    }

    private fun setNavController() {
        navController = (supportFragmentManager
            .findFragmentById(R.id.nav_host_main) as NavHostFragment).navController
    }

    private fun setNavigationView(drawerLayout: DrawerLayout?) {
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener {
            drawerLayout?.closeDrawer(GravityCompat.START)
            it.onNavDestinationSelected(navController)
            true
        }
        navigationView.setupWithNavController(navController)
    }

    private fun setAppBarConfiguration(): DrawerLayout? {
        val drawerLayout: DrawerLayout? = findViewById(R.id.dwl_root)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home
            ),
            drawerLayout
        )
        return drawerLayout
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        if (navigationView == null) {
            menuInflater.inflate(R.menu.menu_drawer, menu)
            return true
        }
        return retValue
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    companion object {
        var drawerLayout: DrawerLayout? = null
    }

}
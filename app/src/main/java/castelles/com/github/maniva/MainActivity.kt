package castelles.com.github.maniva

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.View.*
import android.widget.Button
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import castelles.com.github.maniva.controller.GoogleSignInController
import castelles.com.github.maniva.util.navigate
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var drawerLayout: DrawerLayout? = null

    private lateinit var mGoogleSignInController: GoogleSignInController

    override fun onStart() {
        super.onStart()
        hideStatusBar()
        mGoogleSignInController = GoogleSignInController(object: GoogleSignInController.SignInCallback {
            override fun onSuccess(account: GoogleSignInAccount?) {
                updateName(account)
            }

            override fun onFailure(error: ApiException) {
                Log.e("GoogleSignIn", error.message ?: error.toString())
            }
        })
        mGoogleSignInController.buildSignInScope(this)
    }

    override fun onResume() {
        super.onResume()
        setNavController()
        drawerLayout = setAppBarConfiguration()
        setNavigationView(drawerLayout)

        updateHeaderMenu()
    }

    private fun updateHeaderMenu() {
        mGoogleSignInController.getLastAccount(this) {
            MainScope().launch {
                delay(1000)
                updateName(it)
            }
        }
    }

    private fun updateName(user: GoogleSignInAccount?) {
        val header = findViewById<NavigationView>(R.id.nav_view)
        if (user != null) {

            header.apply {
                getHeaderView(0)
                    .findViewById<TextView>(R.id.txv_menu_login)
                    .text = getString(R.string.str_hello_user, user.givenName)

                findViewById<SignInButton>(R.id.btn_sign_in).visibility = GONE
                findViewById<Button>(R.id.btn_log_out).visibility = VISIBLE
            }
            drawerLayout?.close()
        } else {
            header.apply {
                getHeaderView(0)
                    .findViewById<TextView>(R.id.txv_menu_login)
                    .text = getString(R.string.str_login_to_use_app)

                findViewById<SignInButton>(R.id.btn_sign_in).visibility = VISIBLE
                findViewById<Button>(R.id.btn_log_out).visibility = GONE
            }
        }
        drawerLayout?.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GoogleSignInController.RC_SIGN_IN) {
            mGoogleSignInController.executeSingInTask(data)
        }
    }

    private fun setNavController() {
        navController = (supportFragmentManager
            .findFragmentById(R.id.nav_host_main) as NavHostFragment).navController
    }

    private fun setNavigationView(drawerLayout: DrawerLayout?) {
        navigationView = findViewById(R.id.nav_view)
        navigationView.apply {

            setNavigationItemSelectedListener { item ->
                drawerLayout?.closeDrawer(GravityCompat.START)
                item.navigate(navController)
                true
            }

            setupWithNavController(navController)
            setHeaderLoginAction()
        }
    }

    private fun NavigationView.setHeaderLoginAction() {
        val header = getHeaderView(0)
        header.findViewById<SignInButton>(R.id.btn_sign_in).setOnClickListener {
            mGoogleSignInController.signIn(this@MainActivity)
        }
        header.findViewById<Button>(R.id.btn_log_out).setOnClickListener {
            mGoogleSignInController.signOut {
                updateName(null)
            }
        }
    }

    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(SYSTEM_UI_FLAG_FULLSCREEN, SYSTEM_UI_FLAG_FULLSCREEN)
        } else {
            window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
            actionBar?.hide()
        }
    }

    private fun setAppBarConfiguration(): DrawerLayout? {
        val drawerLayout: DrawerLayout? = findViewById(R.id.dwl_root)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.menu,
                R.id.orders,
                R.id.cart
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
    }

}
package com.wricor.powertechs.view.ui.activities

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wricor.powertechs.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var firebaseAuth: FirebaseAuth

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Firebase
        firebaseAuth = Firebase.auth

        // Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // Bottom Naigation
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationMenu)
        bottomNavigationView.setOnItemSelectedListener {
            val contr = Navigation.findNavController(this, R.id.nav_host_fragment)
            when(it.itemId){
                R.id.bottom_products -> {
                    contr.navigate(R.id.productsFragment)
                    true
                }
                R.id.bottom_requests -> {
                    contr.navigate(R.id.requestsFragment)
                    true
                }
                R.id.bottom_comments -> {
                    contr.navigate(R.id.commentFragment)
                    true
                }
                R.id.bottom_profile -> {
                    contr.navigate(R.id.profileFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val controller = Navigation.findNavController(this, R.id.nav_host_fragment)
        when (item.itemId) {
            R.id.nav_item_perfil -> {
                controller.navigate(R.id.profileFragment)
            }
            /*R.id.nav_item_editar_perfil -> {
                controller.navigate(R.id.editProfileFragment)
            }*/
            R.id.nav_item_carrito -> {
                controller.navigate(R.id.shopFragment)
            }
            R.id.nav_item_productos -> {
                controller.navigate(R.id.productsFragment)
            }
            R.id.nav_item_comentarios -> {
                controller.navigate(R.id.commentFragment)
            }
            R.id.nav_item_contactenos -> {
                controller.navigate(R.id.contactFragment)
            }
            R.id.nav_item_cerrar -> {
                firebaseAuth.signOut()
                controller.navigate(R.id.action_productsFragment_to_loginActivity)
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        return
    }
}
package eu.practice.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private var drawerLayout:DrawerLayout? =null
    private var navigationView: NavigationView? = null
    private var toolbar :Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.DrawerLayout)
        navigationView = findViewById(R.id.navigationView)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        loadFrag(HomeFragment(),0)

        val toggle :ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.OpenDrawer,
            R.string.CloseDrawer
            )

        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

        navigationView?.setNavigationItemSelectedListener{
            item ->
            when(item.itemId){
              R.id.Admin -> {
           loadFrag(AdminFragment(),0)
              }
                R.id.Notes -> {
           loadFrag(NotesFragment(),1)
                }
                R.id.home -> {
           loadFrag(HomeFragment(),1)
                }
                R.id.LogOUt -> {
           loadFrag(LogoutFragment(),1)
                }
                R.id.settings -> {
                    loadFrag(SettingsFragment(),1)
                }
            }

            drawerLayout!!.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)){
            drawerLayout!!.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }

    private fun loadFrag(fragment: Fragment, int :Int){
        val fm : FragmentManager = supportFragmentManager
        val ft : FragmentTransaction = fm.beginTransaction()
        if (int == 0){
            ft.add(R.id.container , fragment)
        }else{
            ft.replace(R.id.container , fragment)
        }

        ft.commit()
    }
}
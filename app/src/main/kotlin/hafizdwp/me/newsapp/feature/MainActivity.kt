package hafizdwp.me.newsapp.feature

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import hafizdwp.me.newsapp.R
import hafizdwp.me.newsapp.feature.base.BaseActivity
import hafizdwp.me.newsapp.feature.explore.ExploreFragment
import hafizdwp.me.newsapp.feature.home.HomeFragment
import hafizdwp.me.newsapp.util.Constant
import hafizdwp.me.newsapp.util.SharedPref
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_toolbar.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    val sp: SharedPref = SharedPref.getInstance()
    val homeFragment: HomeFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //setup BottomNavigationView
        setupBottomNav()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        val tobechecked = sp.getString(Constant.Pref.LANGUAGE, "id")
        when(tobechecked){
            "id" -> nav_view.setCheckedItem(R.id.main_drawer_id)
            "us" -> nav_view.setCheckedItem(R.id.main_drawer_us)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.main_drawer_id -> {
                sp.putString(Constant.Pref.LANGUAGE, "id")
                nav_view.setCheckedItem(R.id.main_drawer_id)
            }
            R.id.main_drawer_us -> {
                sp.putString(Constant.Pref.LANGUAGE, "us")
                nav_view.setCheckedItem(R.id.main_drawer_us)
            }
        }
        homeFragment.getHeadline()
        return true
    }

    private fun setupBottomNav(){
        main_bottomnav.setOnNavigationItemSelectedListener { item: MenuItem ->
            var selectedFragment: Fragment? = null
            when(item.itemId){
                R.id.main_nav_home -> {
                    //fragment home
                    selectedFragment = homeFragment
                }
                R.id.main_nav_explore -> {
                    //fragment explore
                    selectedFragment = ExploreFragment()
                }
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_framelayout, selectedFragment)
            transaction.commit()
            return@setOnNavigationItemSelectedListener true
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_framelayout, homeFragment)
        transaction.commit()
    }
}

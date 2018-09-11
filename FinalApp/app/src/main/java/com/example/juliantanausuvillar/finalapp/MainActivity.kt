package com.example.juliantanausuvillar.finalapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Toast
import com.example.juliantanausuvillar.finalapp.activities.LoginActivity
import com.example.juliantanausuvillar.finalapp.adapters.PagerAdapter
import com.example.juliantanausuvillar.finalapp.fragments.ChatFragment
import com.example.juliantanausuvillar.finalapp.fragments.InfoFragment
import com.example.juliantanausuvillar.finalapp.fragments.RatesFragment
import com.example.mylibrary.ToolbarActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : ToolbarActivity() {




    private var prevBottomSelected: MenuItem? = null  // la anterior seleccionada


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarToLoad(toolbarView as Toolbar)


        setUpViewPager(getPagerAdapter())
        setUpBottomNavigationBar()

    }

    private fun getPagerAdapter(): PagerAdapter {
        var adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(InfoFragment())
        adapter.addFragment(RatesFragment())
        adapter.addFragment(ChatFragment())
        return adapter
    }

    private fun setUpViewPager(adapter: PagerAdapter){
        viewPager.adapter = adapter
        // para que no se destuyan de memoria las pestañas, mas recursos pero menos peticiones
        viewPager.offscreenPageLimit = adapter.count
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(position: Int) {
                if(prevBottomSelected == null){
                    bottomNavigation.menu.getItem(0).isChecked = false
                }else {
                    prevBottomSelected!!.isChecked = false
                }
                bottomNavigation.menu.getItem(position).isChecked = true
                prevBottomSelected = bottomNavigation.menu.getItem(position)
            }

        })
    }

    private fun setUpBottomNavigationBar(){
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.bottom_nav_info -> {
                    viewPager.currentItem = 0; true
                }
                R.id.bottom_nav_rates -> {
                    viewPager.currentItem = 1; true
                }
                R.id.bottom_nav_chat -> {
                    viewPager.currentItem = 2; true
                }

                else -> false

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.general_options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_log_out -> {
                FirebaseAuth.getInstance().signOut()
                goToActivity<LoginActivity> {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

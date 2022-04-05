package com.example.backstack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nv = findViewById<NavigationView>(R.id.nv)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        nv.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            when (it.itemId) {
                R.id.menuMap -> {
                    supportFragmentManager.commit {
                        replace(R.id.frame1, createMapFrag())
                        addToBackStack(null)
                    }
                    true
                }
                R.id.menuItemSetMapStyle -> {
                    supportFragmentManager.commit {
                        replace(R.id.frame1, createStyleFrag())
                        addToBackStack(null)
                    }

                    true

                }
                R.id.menuItemSetLocation -> {
                    supportFragmentManager.commit {
                        replace(R.id.frame1, createLocFrag())
                        addToBackStack(null)
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }
        showMap()
    }

    private fun showMap() {
        supportFragmentManager.commit {
            replace(R.id.frame1, createMapFrag())
        }
    }

    private fun createMapFrag(): MapFragment{
        return MapFragment()
    }

    private fun createStyleFrag() : StyleFragment {
        return StyleFragment {
            val mf = MapFragment()
            mf.setPendingStyle(it)
            showMapWithBackStackAdd(mf)
        }
    }

    private fun createLocFrag(): SetLocFragment {
        return SetLocFragment { lon, lat ->
            val mf = MapFragment()
            mf.setPendingPosition(lon, lat)
            showMapWithBackStackAdd(mf)
        }
    }

    private fun showMapWithBackStackAdd(mf: MapFragment) {
        supportFragmentManager.commit {
            replace(R.id.frame1, mf)
            addToBackStack(null)
        }
    }
}
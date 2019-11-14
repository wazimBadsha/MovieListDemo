package com.example.movielistdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.movielistdemo.R
import com.example.movielistdemo.fragments.HomeUpdatedFragment
import com.example.movielistdemo.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title= ""
        supportActionBar?.hide()
        val adapter = GooglePlusFragmentPageAdapter(supportFragmentManager)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = adapter.count - 1
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    view_pager.currentItem = 0
                    supportActionBar?.hide()
                    true
                }
                R.id.settings -> {
                    view_pager.currentItem = 1
                    supportActionBar?.show()
                    true
                }
                else -> false
            }
        }
    }

    private class GooglePlusFragmentPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> HomeUpdatedFragment()
                1 -> SettingsFragment()
                else -> HomeUpdatedFragment()
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }
}

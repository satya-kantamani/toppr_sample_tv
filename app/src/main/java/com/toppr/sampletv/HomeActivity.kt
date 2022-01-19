package com.toppr.sampletv

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.lang.ref.WeakReference

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction()
            .add(R.id.tab_fcv, TabFragment(object : TabFragment.Companion.TabControl {
                override fun selectedTabIndex(tabIndex: Int) {
                    updateFragment(tabIndex)
                }
            }))
            .commit()
    }

    private fun updateFragment(tabIndex: Int) {
        when (tabIndex) {
            0 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_fcv, SearchFragment())
                    .commit()
            }
            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_fcv, HomeFragment())
                    .commit()
            }
            2 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_fcv, SearchFragment())
                    .commit()
            }
            3 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_fcv, ProfileFragment())
                    .commit()
            }
            else -> {
                Log.d("TAG", "updateFragment: Invalid Tab Index")
            }
        }
    }

    companion object {
        fun startActivity(weakReference: WeakReference<Activity>) {
            val intent = Intent(weakReference.get(), HomeActivity::class.java)
            weakReference.get()?.startActivity(intent)
        }
    }
}
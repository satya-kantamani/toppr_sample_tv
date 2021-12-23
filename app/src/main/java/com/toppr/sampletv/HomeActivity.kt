package com.toppr.sampletv

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: VerticalViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab_layout);

        viewPagerAdapter = ViewPagerAdapter(getSupportFragmentManager());
        viewPager.adapter = viewPagerAdapter;

        setupTabLayout(viewPager, viewPagerAdapter, tabLayout)
    }

    private fun setupTabLayout(
        viewPager: ViewPager,
        viewPagerAdapter: ViewPagerAdapter,
        tabLayout: TabLayout
    ) {
        tabLayout.setupWithViewPager(viewPager)
        val length = tabLayout.tabCount
        for (i in 0 until length) {
            tabLayout.getTabAt(i)?.customView = viewPagerAdapter.getTabView(i, this)
        }
    }

    companion object {
        fun startActivity(weakReference: WeakReference<Activity>) {
            val intent = Intent(weakReference.get(), HomeActivity::class.java)
            weakReference.get()?.startActivity(intent)
        }
    }
}

class ViewPagerAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {
    private val mIconList = listOf(
        R.drawable.ic_graduationcap,
        R.drawable.ic_house,
        R.drawable.ic_magnifyingglass,
        R.drawable.ic_user
    )

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = HomeFragment()
            2 -> fragment = SearchFragment()
            3 -> fragment = ProfileFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 4
    }

    fun getTabView(position: Int, context: Context): View? {
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_tab, null)
        val icon: AppCompatImageView = view.findViewById<View>(R.id.iv) as AppCompatImageView
        /*val layout = view.findViewById<View>(R.id.layout) as ViewGroup
        layout.setBackgroundResource(this.mColorList.get(position))*/
        icon.setImageResource(this.mIconList.get(position))
        //title.text = getPageTitle(position)
        return view
    }
}
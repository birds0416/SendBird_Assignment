package com.assignment.sendbird

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.assignment.sendbird.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PagerAdapter(supportFragmentManager)

        adapter.addFragment(NewFragment(), "New")
        adapter.addFragment(SearchFragment(), "Search")
        adapter.addFragment(BookmarkFragment(), "Bookmark")

        val view_pager : ViewPager = findViewById(R.id.view_pager)
        val tab_layout : TabLayout = findViewById(R.id.tablayout)
        view_pager.adapter = adapter
        tab_layout.setupWithViewPager(view_pager)
    }
}
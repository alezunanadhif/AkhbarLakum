package com.nadhif.akhbar_lakum

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.tabs.TabLayoutMediator
import com.nadhif.akhbar_lakum.adapter.SectionPagerAdapter
import com.nadhif.akhbar_lakum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)
		setUpViewPager()
	}

	private fun setUpViewPager() {

		val adapter = SectionPagerAdapter(this)
		binding.vpNews.adapter = adapter

		TabLayoutMediator(binding.tabs, binding.vpNews) { tab, position ->
			when (position) {
				0 -> tab.icon = ContextCompat.getDrawable(this, R.drawable.world)
				1 -> tab.icon = ContextCompat.getDrawable(this, R.drawable.home)
				2 -> tab.icon = ContextCompat.getDrawable(this, R.drawable.quran)
				3 -> tab.icon = ContextCompat.getDrawable(this, R.drawable.aljazeera)
				4 -> tab.icon = ContextCompat.getDrawable(this, R.drawable.warning)
			}
		}.attach()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)
		val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
		(menu?.findItem(R.id.menu_search)?.actionView as androidx.appcompat.widget.SearchView).apply {
			setSearchableInfo(searchManager.getSearchableInfo(componentName))
			return true
		}
	}
}
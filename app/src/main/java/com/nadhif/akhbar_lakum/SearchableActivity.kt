package com.nadhif.akhbar_lakum

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nadhif.akhbar_lakum.databinding.ActivitySearchableBinding
import com.nadhif.akhbar_lakum.adapter.NewsAdapter
import com.nadhif.akhbar_lakum.data.repository.NewsRepository
import com.nadhif.akhbar_lakum.ui.NewsViewModel
import com.nadhif.akhbar_lakum.utils.NewsViewModelFactory

class SearchableActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchableBinding

    private val searchNewsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository())
    }

    private var querySearch: String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleIntentFromMainActivity(intent)

        searchNewsViewModel.searchNews.observe(this@SearchableActivity) { dataSearch ->
            binding.apply {
                if (dataSearch.articles.isEmpty()) {
                    tvNoNews.text = "No News"
                    tvNoNews.visibility = View.VISIBLE
                } else {
                    rvSearchResults.apply {
                        val mAdapter = NewsAdapter()
                        mAdapter.setData(dataSearch.articles)

                        adapter = mAdapter
                        layoutManager = LinearLayoutManager(this@SearchableActivity)
                        visibility = View.VISIBLE
                    }
                }
            }
        }

        searchNewsViewModel.isLoading.observe(this@SearchableActivity) {
            showLoading(it)
        }

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntentFromMainActivity(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingView.root.visibility = View.VISIBLE
        } else {
            binding.loadingView.root.visibility = View.GONE
        }
    }

    private fun handleIntentFromMainActivity(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY?.also { query ->
                querySearch = query

                binding.apply {
                    rvSearchResults.visibility = View.GONE
                    tvNoNews.visibility = View.VISIBLE
                    searchView.setQuery("", false)
                    searchView.queryHint = query
                    searchView.clearFocus()
                }

                doMySearch(query)
            })
        }
    }

    private fun doMySearch(query: String) {
        searchNewsViewModel.getSearchNews(query)
    }
}
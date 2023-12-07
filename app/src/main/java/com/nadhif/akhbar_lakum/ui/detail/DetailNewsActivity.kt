package com.nadhif.akhbar_lakum.ui.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.nadhif.akhbar_lakum.databinding.ActivityDetailNewsBinding
import com.nadhif.akhbar_lakum.data.model.ArticlesItem
import com.squareup.picasso.Picasso

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding

    companion object{
        const val DATA_NEWS = "data_news"
        const val DATE_NEWS = "date_news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarDetail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail News"
        }

        val dataNews = intent.getParcelableExtra<ArticlesItem>(DATA_NEWS)
        val dataDate = intent.getStringExtra(DATE_NEWS)

        setupMyXml(dataNews, dataDate)
        setupWebView(dataNews)
    }

    private fun setupWebView(dataNews: ArticlesItem?) {
        val webSetting = binding.wvDetail.settings
        webSetting.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        dataNews?.url?.let { binding.wvDetail.loadUrl(it) }
    }

    private fun setupMyXml(dataNews: ArticlesItem?, dataDate: String?) {
        binding.apply {
            detailTitle.text = dataNews?.title
            detailAuthor.text = dataNews?.author
            detailPublishedAt.text = dataDate

            Picasso.get()
                .load(dataNews?.urlToImage)
                .fit()
                .centerInside()
                .into(detailImage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
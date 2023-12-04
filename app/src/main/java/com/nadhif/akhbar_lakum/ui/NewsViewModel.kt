package com.nadhif.akhbar_lakum.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nadhif.akhbar_lakum.data.model.NewsResponse
import com.nadhif.akhbar_lakum.data.repository.NewsRepository

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    val worldNews: LiveData<NewsResponse> = repository.worldNews
    val commonMuslimNews: LiveData<NewsResponse> = repository.commonMuslimNews
    val aboutAlQuranNews: LiveData<NewsResponse> = repository.aboutAlQuranNews
    val alJazeeraNews: LiveData<NewsResponse> = repository.alJazeeraNews
    val warningForMuslimNews: LiveData<NewsResponse> = repository.warningForMuslimNews
    val searchNews: LiveData<NewsResponse> = repository.searchNews
    val isLoading: LiveData<Boolean> = repository.isLoading

    fun getWorldNews() {
        repository.getWorldNews()
    }

    fun getCommonMuslimNews() {
        repository.getCommonMuslimNews()
    }

    fun getAboutAlQuranNews() {
        repository.getAboutAlQuranNews()
    }

    fun getAlJazeraNews() {
        repository.getAlJazeeraNews()
    }

    fun getWarningNews() {
        repository.getWarningForMuslimNews()
    }

    fun getSearchNews(q: String) {
        repository.getSearchNews(q)
    }
}
package com.nadhif.akhbar_lakum.data.network

import com.nadhif.akhbar_lakum.data.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/everything")
    fun getWorldNews(
        @Query("q")  q:String = "world",
        @Query("language") language: String = "en",
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortBy") sortBy: String = "popularity"
    ): Call<NewsResponse>

    @GET("/v2/everything")
    fun getCommonMuslimNews(
        @Query("q")  q:String = "islam",
        @Query("language") language: String = "en",
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortBy") sortBy: String = "popularity"
    ): Call<NewsResponse>

    @GET("/v2/everything")
    fun getAlQuranNews(
        @Query("q") q: String = "al-quran",
        @Query("language") language: String = "en"
    ): Call<NewsResponse>


    @GET("/v2/top-headlines")
    fun getAlJazeeraNews(
        @Query("sources") sources: String = "al-jazeera-english"
    ): Call<NewsResponse>


    @GET("/v2/everything")
    fun getWarningForMuslimNews(
        @Query("q") q: String = "anti-islam",
        @Query("language") language: String = "en"
    ): Call<NewsResponse>


    @GET("/v2/everything")
    fun getSearchNews(
        @Query("q") q: String,
        @Query("pageSize") pageSize: Int = 19,
    ): Call<NewsResponse>
}

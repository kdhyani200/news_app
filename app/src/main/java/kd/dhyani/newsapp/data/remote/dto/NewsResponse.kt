package kd.dhyani.newsapp.data.manager.remote.dto

import kd.dhyani.newsapp.domain.manager.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
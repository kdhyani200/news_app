package kd.dhyani.newsapp.presentation.bookmark

import kd.dhyani.newsapp.domain.manager.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)

package kd.dhyani.newsapp.presentation.onboarding.search

import androidx.paging.PagingData
import kd.dhyani.newsapp.domain.manager.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
)

package kd.dhyani.newsapp.presentation.onboarding.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.news.NewsUseCases
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews -> {
                searchNews()
            }

            else -> {}
        }
    }

    private fun searchNews() {
        val articles = newsUseCases.searchNews(
            searchQuery = _state.value.searchQuery,
            source = listOf("google-news-in","the-hindu","the-times-of-india","abc-news",
                "al-jazeera-english","bloomberg","business-insider","fortune","the-wall-street-journal",
                "ars-technica","associated-press","cnn","espn-cric-info","fortune","fox-news","hacker-news",
                "nbc-news","new-scientist","the-washington-post","time",)
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles = articles)
    }
}
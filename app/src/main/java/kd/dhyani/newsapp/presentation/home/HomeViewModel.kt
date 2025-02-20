package kd.dhyani.newsapp.presentation.onboarding.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.news.NewsUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {
    val news = newsUseCases.getNews(
        sources = listOf("google-news-in","the-hindu","the-times-of-india","abc-news",
            "al-jazeera-english","bloomberg","business-insider","fortune","the-wall-street-journal",
            "ars-technica","associated-press","cnn","espn-cric-info","fortune","fox-news","hacker-news",
            "nbc-news","new-scientist","the-washington-post","time",)
    ).cachedIn(viewModelScope)
}
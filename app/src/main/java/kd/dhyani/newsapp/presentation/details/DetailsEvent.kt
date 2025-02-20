package kd.dhyani.newsapp.presentation.onboarding.details.components

import kd.dhyani.newsapp.domain.manager.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}
package kd.dhyani.newsapp.domain.manager.usecases.app_entry.news

import kd.dhyani.newsapp.domain.manager.model.Article
import kd.dhyani.newsapp.domain.manager.repository.NewsRepository

class DeleteArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}
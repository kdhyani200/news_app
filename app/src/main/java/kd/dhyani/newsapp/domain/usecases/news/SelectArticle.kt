package kd.dhyani.newsapp.domain.manager.usecases.app_entry.news

import kd.dhyani.newsapp.domain.manager.model.Article
import kd.dhyani.newsapp.domain.manager.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectArticle(url)
    }
}
package kd.dhyani.newsapp.domain.manager.usecases.app_entry.news

import kd.dhyani.newsapp.domain.manager.model.Article
import kd.dhyani.newsapp.domain.manager.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>>{
        return newsRepository.selectArticles()
    }
}
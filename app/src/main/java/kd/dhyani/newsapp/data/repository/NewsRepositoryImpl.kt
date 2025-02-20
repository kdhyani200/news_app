package kd.dhyani.newsapp.data.manager.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kd.dhyani.newsapp.data.manager.local.NewsDao
import kd.dhyani.newsapp.data.manager.remote.dto.NewsApi
import kd.dhyani.newsapp.data.manager.remote.dto.NewsPagingSource
import kd.dhyani.newsapp.data.manager.remote.dto.SearchNewsPagingSource
import kd.dhyani.newsapp.domain.manager.model.Article
import kd.dhyani.newsapp.domain.manager.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString (separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}
package kd.dhyani.newsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kd.dhyani.newsapp.data.manager.LocalUserManagerImpl
import kd.dhyani.newsapp.data.manager.local.NewsDao
import kd.dhyani.newsapp.data.manager.local.NewsDatabase
import kd.dhyani.newsapp.data.manager.local.NewsTypeConvertor
import kd.dhyani.newsapp.data.manager.remote.dto.NewsApi
import kd.dhyani.newsapp.data.manager.repository.NewsRepositoryImpl
import kd.dhyani.newsapp.domain.manager.LocalUserManager
import kd.dhyani.newsapp.domain.manager.repository.NewsRepository
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.AppEntryUseCases
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.ReadAppEntry
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.SaveAppEntry
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.news.DeleteArticle
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.news.GetNews
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.news.NewsUseCases
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.news.SearchNews
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.news.SelectArticle
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.news.SelectArticles
import kd.dhyani.newsapp.domain.manager.usecases.app_entry.news.UpsertArticle
import kd.dhyani.newsapp.util.Constants.BASE_URL
import kd.dhyani.newsapp.util.Constants.NEWS_DATABASE_NAME
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi,newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newDao
}

package kd.dhyani.newsapp.presentation.onboarding.common

import EmptyScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import kd.dhyani.newsapp.domain.manager.model.Article
import kd.dhyani.newsapp.presentation.onboarding.Dimensions.ExtraSmallPadding2
import kd.dhyani.newsapp.presentation.onboarding.Dimensions.MediumPadding1


@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    article: List<Article>,
    onclick: (Article) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(count = article.size) {
            val article = article[it]
            ArticleCard(article = article, onclick = { onclick(article) })
        }
    }
}


@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    article: LazyPagingItems<Article>,
    onclick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(
        articles = article,
        onRefresh = { article.refresh() }
    )
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = article.itemCount) {
                article[it]?.let {
                    ArticleCard(article = it, onclick = { onclick(it) })
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>,
    onRefresh: () -> Unit
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(onRefresh = onRefresh)
            false
        }

        else -> {
            true
        }
    }
}


@Composable
private fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MediumPadding1)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}

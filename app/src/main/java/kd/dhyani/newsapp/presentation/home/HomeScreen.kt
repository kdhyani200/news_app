package kd.dhyani.newsapp.presentation.onboarding.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kd.dhyani.newsapp.R
import kd.dhyani.newsapp.domain.manager.model.Article
import kd.dhyani.newsapp.presentation.onboarding.Dimensions.MediumPadding1
import kd.dhyani.newsapp.presentation.onboarding.common.ArticleCardShimmerEffect
import kd.dhyani.newsapp.presentation.onboarding.common.ArticlesList
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    val audiowideFontFamily = FontFamily(Font(R.font.audiowide))

    val logoText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFF0A2390))) { append("Fast") }
        withStyle(style = SpanStyle(color = Color(0xFF1350D2))) { append("News") }
    }

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(0..9)
                    .joinToString(separator = " \uD83D\uDFE5") { it.title }
            } else ""
        }
    }

    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            isRefreshing = true
        }
    ) {
        LaunchedEffect(isRefreshing) {
            if (isRefreshing) {
                articles.refresh()
                delay(1500)
                isRefreshing = false
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = MediumPadding1)
                .statusBarsPadding()
        ) {
            Text(
                text = logoText,
                fontSize = 30.sp,
                fontFamily = audiowideFontFamily,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = titles,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MediumPadding1)
                    .basicMarquee(),
                fontSize = 12.sp,
                color = colorResource(id = R.color.placeholder)
            )

            Spacer(modifier = Modifier.padding(10.dp))

            if (isRefreshing) {
                repeat(5) {
                    ArticleCardShimmerEffect(modifier = Modifier.padding(horizontal = MediumPadding1))
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            } else {
                ArticlesList(
                    modifier = Modifier.padding(2.dp),
                    article = articles,
                    onclick = { navigateToDetails(it) }
                )
            }
        }
    }
}


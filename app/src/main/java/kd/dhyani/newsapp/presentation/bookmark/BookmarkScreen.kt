package kd.dhyani.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kd.dhyani.newsapp.R
import kd.dhyani.newsapp.domain.manager.model.Article
import kd.dhyani.newsapp.presentation.onboarding.common.ArticlesList

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 19.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 9.dp),
            text = "Bookmarks",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            color = colorResource(id = R.color.text_title)
        )


        Spacer(modifier = Modifier.height(11.dp))

        if (state.articles.isEmpty()) {
            EmptyState()
        } else {
            ArticlesList(article = state.articles, onclick = { navigateToDetails(it) })
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.bookmark),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = colorResource(id = R.color.shimmer)
        )

        Spacer(modifier = Modifier.height(10.dp)) // Space between icon and text

        Text(
            text = "No saved articles",
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.shimmer)
        )
    }
}

package kd.dhyani.newsapp.presentation.onboarding.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kd.dhyani.newsapp.R
import kd.dhyani.newsapp.domain.manager.model.Article
import kd.dhyani.newsapp.presentation.onboarding.Dimensions.ArticleCardSize
import kd.dhyani.newsapp.presentation.onboarding.Dimensions.ExtraSmallPadding
import kd.dhyani.newsapp.presentation.onboarding.Dimensions.ExtraSmallPadding2
import kd.dhyani.newsapp.presentation.onboarding.Dimensions.SmallIconSize

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onclick:() -> Unit
){
    val context = LocalContext.current
    Row(modifier = modifier.clickable { onclick() }) {
        AsyncImage(
            modifier = modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )

                Spacer(modifier = modifier.width(ExtraSmallPadding2))

                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = "Time",
                    modifier = modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.body)
                )

                Spacer(modifier = modifier.width(ExtraSmallPadding2))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}
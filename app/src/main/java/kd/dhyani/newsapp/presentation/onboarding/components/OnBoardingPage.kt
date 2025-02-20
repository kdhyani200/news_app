package kd.dhyani.newsapp.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kd.dhyani.newsapp.R
import kd.dhyani.newsapp.presentation.onboarding.Dimensions.MediumPadding1
import kd.dhyani.newsapp.presentation.onboarding.Dimensions.MediumPadding2
import kd.dhyani.newsapp.presentation.onboarding.Page

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = Modifier) {
        Image(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.6f),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = page.image),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
            )
        Text(text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

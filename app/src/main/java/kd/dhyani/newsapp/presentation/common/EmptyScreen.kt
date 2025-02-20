
import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import kd.dhyani.newsapp.R
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    onRefresh: () -> Unit
) {
    var message by remember { mutableStateOf(parseErrorMessage(error)) }
    var icon by remember { mutableStateOf(R.drawable.network_error) }

    if (error == null) {
        message = "Network Error"
        icon = R.drawable.wifi
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    var isRefreshing by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = colorResource(id = R.color.text_medium),
            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnimation)
        )
        Text(
            text = message,
            modifier = Modifier.padding(5.dp).alpha(alphaAnimation),
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                isRefreshing = true
                onRefresh()
                isRefreshing = false
            }
        ) {
            Text(text = "Retry")
        }

        if (isRefreshing) {
            Spacer(modifier = Modifier.height(16.dp))
            ShimmerEffect()
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(3) {
            Surface(
                modifier = Modifier
                    .size(width = 250.dp, height = 20.dp)
                    .alpha(0.3f),
                color = Color.Gray
            ) {}
        }
    }
}

fun parseErrorMessage(error: LoadState.Error?): String {
    return when (error?.error) {
        is SocketTimeoutException -> "Server Unavailable"
        is ConnectException -> "Internet Unavailable"
        else -> "Unknown Error"
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreview() {
    EmptyScreen(onRefresh = {})
}

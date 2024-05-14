package com.example.learnjetpackcompose

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpackcompose.ui.theme.LearnJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeArticleImage(stringResource(id = R.string.title_jetpack_compose), stringResource(
                        id = R.string.first_paragraph_content), stringResource(id = R.string.second_paragraph_content))
                }
            }
        }
    }
}

@Composable
fun ComposeArticleText(title: String, contentParagraph1: String, contentParagraph2: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier,
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(
            text = contentParagraph1,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = contentParagraph2,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun ComposeArticleImage(title: String, contentParagraph1: String, contentParagraph2: String, modifier: Modifier = Modifier) {
    val bannerImage = painterResource(R.drawable.bg_compose_background)
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Image(
            painter = bannerImage,
            contentDescription = null,
//            Modifier.fillMaxWidth(1f)
        )

        ComposeArticleText(
            title = stringResource(id = R.string.title_jetpack_compose),
            contentParagraph1 = stringResource(id = R.string.first_paragraph_content),
            contentParagraph2 = stringResource(id = R.string.second_paragraph_content),
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnJetpackComposeTheme {
        ComposeArticleImage(stringResource(R.string.title_jetpack_compose), stringResource(id = R.string.first_paragraph_content), stringResource(
            id = R.string.second_paragraph_content)
        )
    }
}
package com.example.businesscard

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF073042)
                ) {
                    CompleteCard()
                }
            }
        }
    }
}

@Composable
fun CompleteCard(modifier: Modifier = Modifier) {
    val androidLogo = painterResource(id = R.drawable.android_logo)
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(painter = androidLogo, contentDescription = null,
            modifier = Modifier
                .height(100.dp)
        )
        Text(
            text = stringResource(id = R.string.full_name),
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = stringResource(id = R.string.title),
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold, color = Color(0xFF3ddc84)
        )
    }
    Column(verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(5.dp)
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(modifier = modifier) {
            Icon(
                Icons.Filled.Call,
                contentDescription = "Phone Number",
                tint = Color.Green,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.phone_number),
            )
        }
        Row(modifier = modifier) {
            Icon(
                Icons.Filled.Share,
                contentDescription = "Github Profile",
                tint = Color.Green,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.github_link),
            )
        }
        Row(modifier = modifier) {
            Icon(
                Icons.Filled.Email,
                contentDescription = "Gmail",
                tint = Color.Green,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.email),
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CompleteCardPreview() {
    BusinessCardTheme {
        CompleteCard()
    }
}
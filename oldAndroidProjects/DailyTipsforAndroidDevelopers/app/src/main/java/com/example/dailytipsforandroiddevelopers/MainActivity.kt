package com.example.dailytipsforandroiddevelopers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dailytipsforandroiddevelopers.model.BonusDataSource
import com.example.dailytipsforandroiddevelopers.model.TipsDataSource
import com.example.dailytipsforandroiddevelopers.ui.theme.DailyTipsForAndroidDevelopersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTipsForAndroidDevelopersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    TipsApp()
                }
            }
        }
    }

    @Composable
    fun TipsApp() {
        Scaffold(
//            modifier = Modifier.fillMaxWidth(),
            topBar = { TopAppBar() }
        ) {/* It is not a good practice to access data source directly from the UI. Will learn more and update later.*/
            val tips = TipsDataSource.listOfTips
            TipsList(listOfTips = tips, bonusList = BonusDataSource.tipBonus, contentPadding = it)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar(modifier: Modifier = Modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        )
    }

    @Preview
    @Composable
    fun TipsApppPreview() {
        DailyTipsForAndroidDevelopersTheme{
            TipsApp()
        }
    }
}


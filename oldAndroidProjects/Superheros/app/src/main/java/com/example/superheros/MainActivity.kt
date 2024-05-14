package com.example.superheros

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
import com.example.superheros.model.HeroesRepository
import com.example.superheros.ui.theme.SuperherosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            SuperherosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroesApp()
                }
            }
        }
    }

    @Composable
    fun SuperheroesApp() {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = { HeroesTopAppBar() }
        ) {
            /* Important: It is not a good practice to access data source directly from the UI.
    In later units you will learn how to use ViewModel in such scenarios that takes the
    data source as a dependency and exposes heroes.
     */
            val heroes = HeroesRepository.heroes
            HeroesList(heroes = heroes, contentPadding = it)
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HeroesTopAppBar(modifier: Modifier = Modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }, modifier = modifier
        )
    }


    @Preview(showBackground = true)
    @Composable
    fun SuperHeroesPreview() {
        SuperherosTheme {
            SuperheroesApp()
        }
    }
}
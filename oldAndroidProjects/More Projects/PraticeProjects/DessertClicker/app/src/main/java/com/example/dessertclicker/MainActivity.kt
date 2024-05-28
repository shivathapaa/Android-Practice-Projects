package com.example.dessertclicker


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.dessertclicker.ui.DessertClickerApp
import com.example.dessertclicker.ui.theme.DessertClickerTheme


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DessertClickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DessertClickerApp()
                }
            }

            Log.d(TAG, "onCreate Called Hai ta")
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called Hai ta")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called Hai ta")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called Hai ta")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called Hai ta")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called Hai ta")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called Hai ta")
    }
}


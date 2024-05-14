package com.example.packagem3.ui.presentation.progress_indicator

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import com.example.packagem3.R

@Composable
fun ProgressIndicatorExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        MyLinearManualProgressIndicator()
        MyLinearIndeterminateProgressIndicator()
        MyCircularManualProgressIndicator()
        MyCircularIndeterminateProgressIndicator()
    }
}

@Composable
fun MyLinearManualProgressIndicator(
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableFloatStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "MyLinearManualProgressIndicator"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        LinearProgressIndicator(
            progress = { animatedProgress }
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.extra_large_padding)))
        Button(onClick = { progress += 0.1f }) {
            Text(text = "Increase")
        }
//        Slider(
//            modifier = Modifier
//                .padding(horizontal = dimensionResource(id = R.dimen.large_padding)),
////                .semantics {
////                    val progressPercent = (progress * 100).toInt()
////                    if (progressPercent in progressBreakpoints) {
////                        stateDescription = "Progress $progressPercent"
////                    }
////                },
//            value = progress,
//            valueRange = 0f..1f,
//            steps = 100,
//            onValueChange = { progress = it },
//        )
    }

}

@Composable
fun MyLinearIndeterminateProgressIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        LinearProgressIndicator()
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
        LinearProgressIndicator(
            trackColor = Color.Red,
            color = Color.Yellow,
            strokeCap = StrokeCap.Round,
        )
    }
}

@Composable
fun MyCircularManualProgressIndicator(
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableFloatStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "MyCircularManualProgressIndicator"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CircularProgressIndicator(progress = { animatedProgress })
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.extra_large_padding)))
        Button(onClick = { progress += 0.1f }) {
            Text(text = "Increase")
        }
    }
}

@Composable
fun MyCircularIndeterminateProgressIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_padding)))
        CircularProgressIndicator(
            strokeWidth = dimensionResource(id = R.dimen.extra_small_padding),
            strokeCap = StrokeCap.Round,
            trackColor = Color.Red,
            color = Color.Yellow
        )
    }
}

private val progressBreakpoints = listOf(20, 40, 60, 80, 100)
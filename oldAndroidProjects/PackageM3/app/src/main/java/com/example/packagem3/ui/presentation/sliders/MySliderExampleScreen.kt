package com.example.packagem3.ui.presentation.sliders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MySliderExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        MySimpleSlider()
        MySimpleStepSlider()
        MyRangeSlider()
        MyStepRangeSlider()
    }
}

@Composable
fun MySimpleSlider(
    modifier: Modifier = Modifier
) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(text = "%.2f".format(sliderPosition))
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
    }
}

@Composable
fun MySimpleStepSlider(
    modifier: Modifier = Modifier
) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(text = "%.2f".format(sliderPosition))
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..60f,
            onValueChangeFinished = { // do your task
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)//
            },
            steps = 5
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRangeSlider(
    modifier: Modifier = Modifier
) {
    val rangeSliderState = remember {
        RangeSliderState(
            activeRangeStart = 0f,
            activeRangeEnd = 100f,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            valueRange = 0f..100f
        )
    }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        val rangeStart = "%.2f".format(rangeSliderState.activeRangeStart)
        val rangeEnd = "%.2f".format(rangeSliderState.activeRangeEnd)
        Text(text = "$rangeStart..$rangeEnd")

        RangeSlider(state = rangeSliderState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStepRangeSlider(
    modifier: Modifier = Modifier
) {
    val rangeSliderState = remember {
        RangeSliderState(
            activeRangeStart = 0f,
            activeRangeEnd = 100f,
            steps = 4,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            valueRange = 0f..100f
        )
    }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        val rangeStart = "%.2f".format(rangeSliderState.activeRangeStart)
        val rangeEnd = "%.2f".format(rangeSliderState.activeRangeEnd)
        Text(text = "$rangeStart..$rangeEnd")

        RangeSlider(state = rangeSliderState)
    }
}


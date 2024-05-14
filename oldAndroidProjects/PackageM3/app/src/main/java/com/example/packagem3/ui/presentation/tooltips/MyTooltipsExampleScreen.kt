package com.example.packagem3.ui.presentation.tooltips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MyTooltipsExampleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        MyPlainTooltip()
        MyPlainTooltipWithManualInvocation()
        MyPlainTooltipWithCaret()
        MyBasicRichTooltipWithCaret()
        MyRichTooltipWithCaret()
        MyRichTooltipWithManualInvocation()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlainTooltip(
    modifier: Modifier = Modifier
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip {
                Text(text = "Add to favourites")
            }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlainTooltipWithManualInvocation(
    modifier: Modifier = Modifier
) {
    val tooltipState = rememberTooltipState()
    val scope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                PlainTooltip {
                    Text(text = "Add to list")
                }
            },
            state = tooltipState
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.requiredHeight(30.dp))
        OutlinedButton(onClick = { scope.launch { tooltipState.show() } }) {
            Text(text = "Display tooltip")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlainTooltipWithCaret(
    modifier: Modifier = Modifier
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip(
                caretProperties = TooltipDefaults.caretProperties
            ) {
                Text(text = "Add to favourites")
            }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBasicRichTooltipWithCaret(
    modifier: Modifier = Modifier
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            RichTooltip {
                Text(text = richTooltipText)
            }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRichTooltipWithCaret(
    modifier: Modifier = Modifier
) {
    val tooltipState = rememberTooltipState()
    val scope = rememberCoroutineScope()

    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            RichTooltip(
                title = { Text(text = richTooltipSubheadText) },
                action = {
                    TextButton(onClick = { scope.launch { tooltipState.dismiss() } }) {
                        Text(text = richTooltipActionText)
                    }
                },
            ) {
                Text(text = richTooltipText)
            }
        },
        state = tooltipState
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRichTooltipWithManualInvocation(
    modifier: Modifier = Modifier
) {
    val tooltipState = rememberTooltipState()
    val scope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            tooltip = {
                RichTooltip(
                    title = { Text(text = richTooltipSubheadText) },
                    action = {
                        TextButton(onClick = { scope.launch { tooltipState.dismiss() } }) {
                            Text(text = richTooltipActionText)
                        }
                    },
                ) {
                    Text(text = richTooltipText)
                }
            },
            state = tooltipState
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.requiredHeight(30.dp))
        OutlinedButton(onClick = { scope.launch { tooltipState.show() } }) {
            Text(text = "Display tooltip")
        }
    }
}

const val richTooltipSubheadText = "Permissions"
const val richTooltipText =
    "Configure permissions for selected service accounts. " +
            "You can add and remove service account members and assign roles to them. " +
            "Visit go/permissions for details"
const val richTooltipActionText = "Request Access"
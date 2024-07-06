import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.AppTheme
import theme.MyThemeColor

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    var themeSelection by remember { mutableStateOf("") }
    var darkMode by remember { mutableStateOf(false) }
    var dynamicTheme by remember { mutableStateOf(false) }

    val selectedTheme = when (themeSelection) {
        "PINK" -> MyThemeColor.PINK
        "YELLOW" -> MyThemeColor.YELLOW
        "GREEN" -> MyThemeColor.GREEN
        "ORANGE" -> MyThemeColor.ORANGE
        "RED" -> MyThemeColor.RED
        "NEO" -> MyThemeColor.NEO
        else -> MyThemeColor.YELLOW
    }

    AppTheme(
        darkTheme = darkMode,
        selectedTheme = selectedTheme,
        dynamicColor = dynamicTheme && dynamicColor
    ) {
//        NormalTest()

        ThemeSelectionButton(
            onGreenClick = { themeSelection = "GREEN" },
            onPinkClick = { themeSelection = "PINK" },
            onYellowClick = { themeSelection = "YELLOW" },
            onRedClick = { themeSelection = "RED" },
            onOrangeClick = { themeSelection = "ORANGE" },
            onNeoGreenClick = { themeSelection = "NEO" },
            onDarkModeClick = { darkMode = !darkMode },
            isDarkMode = darkTheme,
            onDynamicThemeClick = { dynamicTheme = !dynamicTheme },
            isDynamicTheme = dynamicTheme && dynamicColor
        )
    }
}

@Composable
fun ThemeSelectionButton(
    modifier: Modifier = Modifier,
    onGreenClick: () -> Unit,
    onPinkClick: () -> Unit,
    onYellowClick: () -> Unit,
    onRedClick: () -> Unit,
    onOrangeClick: () -> Unit,
    onNeoGreenClick: () -> Unit,
    onDarkModeClick: () -> Unit,
    isDarkMode: Boolean,
    onDynamicThemeClick: () -> Unit,
    isDynamicTheme: Boolean
) {
    Box(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextButton(
                modifier = Modifier,
                onClick = onDarkModeClick
            ) {
                val textToShow = if (isDarkMode) "Light Mode" else "Dark Mode"

                Text(text = textToShow)
            }

            FilterChip(
                selected = isDynamicTheme,
                onClick = onDynamicThemeClick,
                label = { Text(text = "Dynamic Theme (Android)") }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onGreenClick,
                    enabled = !isDynamicTheme,
                    colors = ButtonDefaults.buttonColors()
                ) {
                    Text(text = "Green")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onPinkClick,
                    enabled = !isDynamicTheme
                ) {
                    Text(text = "Pink")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onYellowClick,
                    enabled = !isDynamicTheme
                ) {
                    Text(text = "Yellow")
                }
            }

            Text(
                modifier = Modifier.padding(top = 16.dp), text = "With match color"
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onRedClick,
                    enabled = !isDynamicTheme
                ) {
                    Text(text = "Red")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onOrangeClick,
                    enabled = !isDynamicTheme
                ) {
                    Text(text = "Orange")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onNeoGreenClick,
                    enabled = !isDynamicTheme
                ) {
                    Text(text = "Neo")
                }
            }
        }
    }
}

@Composable
fun NormalTest(modifier: Modifier = Modifier) {
    var showText by remember { mutableStateOf(true) }

    Box(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        AnimatedVisibility(showText) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter).statusBarsPadding(),
                text = "Somethings up with common main.",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { showText = !showText }) {
            Text(
                text = "This is common main!"
            )
        }
    }
}
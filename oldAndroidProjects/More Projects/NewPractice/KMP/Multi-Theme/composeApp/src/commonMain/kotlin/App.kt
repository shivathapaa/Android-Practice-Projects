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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.local.DataStoreRepository
import data.local.createDataStore
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.AppTheme
import theme.MyThemeColor

@Composable
@Preview
fun App(
    context: Any? = null,
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    val scope = rememberCoroutineScope()
    val dataStoreRepository = remember { DataStoreRepository(dataStore = createDataStore(context)) }

    // Bad practice - All these should be from viewModel
    var themeSelection by remember { mutableStateOf("PINK") }
    var darkMode by remember { mutableStateOf(false) }
    var dynamicTheme by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        dataStoreRepository.readSelectedThemeColor().collectLatest {
            themeSelection = it
        }
    }
    LaunchedEffect(Unit) {
        dataStoreRepository.readIsDarkModeSet().collectLatest {
            darkMode = it
        }
    }
    LaunchedEffect(Unit) {
        dataStoreRepository.readIsDynamicColorSet().collectLatest {
            dynamicTheme = it
        }
    }

    val selectedTheme = when (themeSelection) {
        "PINK" -> MyThemeColor.PINK
        "YELLOW" -> MyThemeColor.YELLOW
        "GREEN" -> MyThemeColor.GREEN
        "ORANGE" -> MyThemeColor.ORANGE
        "RED" -> MyThemeColor.RED
        "NEO" -> MyThemeColor.NEO
        else -> MyThemeColor.YELLOW
    }


    fun saveSelectedThemeColor(selectedThemeColor: String) {
        scope.launch {
            dataStoreRepository.saveSelectedThemeColor(selectedThemeColor = selectedThemeColor)
        }
    }

    AppTheme(
        darkTheme = darkMode,
        selectedTheme = selectedTheme,
        dynamicColor = dynamicTheme && dynamicColor
    ) {
//        NormalTest()

        ThemeSelectionButton(
            onGreenClick = {
                saveSelectedThemeColor(selectedThemeColor = "GREEN")
            },
            onPinkClick = {
                saveSelectedThemeColor(selectedThemeColor = "PINK")
            },
            onYellowClick = {
                saveSelectedThemeColor(selectedThemeColor = "YELLOW")
            },
            onRedClick = {
                saveSelectedThemeColor(selectedThemeColor = "RED")
            },
            onOrangeClick = {
                saveSelectedThemeColor(selectedThemeColor = "ORANGE")
            },
            onNeoGreenClick = {
                saveSelectedThemeColor(selectedThemeColor = "NEO")
            },
            onDarkModeClick = {
                scope.launch {
                    dataStoreRepository.saveIsDarkModeSet(isDarkModeSet = !darkMode)
                }
            },
            isDarkMode = darkMode,
            onDynamicThemeClick = {
                scope.launch {
                    dataStoreRepository.saveIsDynamicColorSet(isDynamicColorSet = !dynamicTheme)
                }
            },
            isDynamicTheme = dynamicTheme && dynamicColor,
            showDynamicButton = context != null
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
    isDynamicTheme: Boolean,
    showDynamicButton: Boolean
) {
    Box(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
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
                Text(text = if (isDarkMode) "Light Mode" else "Dark Mode")
            }
            // Don't show for IOS
            if (showDynamicButton) {
                FilterChip(
                    selected = isDynamicTheme,
                    onClick = onDynamicThemeClick,
                    label = { Text(text = "Dynamic Theme (Android)") }
                )
            }

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
                modifier = Modifier.padding(top = 16.dp), text = "With match color",
                color = MaterialTheme.colorScheme.onSurface
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
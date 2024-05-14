package com.example.musicsample.feature.settings.themes_and_colors

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.musicsample.feature.settings.components.SwitchWithText
import com.example.musicsample.feature.settings.components.TwoLineTextWithDialogOpener
import com.example.musicsample.navigation.nav_drawer.settings.SettingsDestinations
import com.example.musicsample.navigation_drawer.presentation.SettingsScaffold

@Composable
fun SettingThemeAndColorScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var checkedState by remember {
        mutableStateOf(false)
    }
    SettingsScaffold(title = SettingsDestinations.ThemesAndColor.title, onNavigateUp = onNavigateUp) {innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            TwoLineTextWithDialogOpener(
                title = "Material You themes",
                description = "Makes you familiar with your choices.",
                onOpenDialogClick = { /*TODO*/ })
            SwitchWithText(
                title = "Use extra dark colors",
                description = "Make screen pitch black for little battery saving on AMOLED screens.",
                checkedState = checkedState,
                onCheckedStateChanged = { checkedState = it }
            )
            TwoLineTextWithDialogOpener(
                title = "Filter song by duration",
                description = "Exclude songs with duration less than specified amount. You can skip shorter audio files.",
                onOpenDialogClick = { /*TODO*/ })
            SwitchWithText(
                title = "Split by category",
                description = "Make different category based on the differ genres like audio book, podcast, audioMovie, novel, religion, etc.",
                checkedState = checkedState,
                onCheckedStateChanged = { checkedState = it }
            )
            TwoLineTextWithDialogOpener(
                title = "Filter song by duration",
                description = "Exclude songs with duration less than specified amount. You can skip shorter audio files.",
                onOpenDialogClick = { /*TODO*/ })
            SwitchWithText(
                title = "Split by category",
                description = "Make different category based on the differ genres like audio book, podcast, audioMovie, novel, religion, etc.",
                checkedState = checkedState,
                onCheckedStateChanged = { checkedState = it }
            )
        }
    }
}
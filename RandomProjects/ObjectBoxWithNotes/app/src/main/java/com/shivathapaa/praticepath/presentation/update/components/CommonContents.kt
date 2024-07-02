package com.shivathapaa.praticepath.presentation.update.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.shivathapaa.praticepath.R
import kotlinx.coroutines.android.awaitFrame


@Composable
fun NoteUpsertBodyContainer(
    modifier: Modifier = Modifier,
    noteUiState: NoteUiState,
    onValueChange: (NoteDetails) -> Unit,
    bodyTextCount: Int,
    timeAndDate: String,
    listState: LazyListState,
    innerPadding: PaddingValues
) {
    val textFieldCustomColors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent, // Hide focused state indicator
        unfocusedIndicatorColor = Color.Transparent, // Hide unfocused state indicator
        disabledIndicatorColor = Color.Transparent, // Hide disabled state indicator...
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        contentPadding = innerPadding,
        state = listState
    ) {
        item {
            TitleField(
                onTitleUpdate = { onValueChange(noteUiState.noteDetails.copy(title = it)) },
                title = noteUiState.noteDetails.title,
                textFieldCustomColors = textFieldCustomColors,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            NoteCharacterCountAndDate(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.medium_padding)),
                characters = bodyTextCount,
                dateAndTime = timeAndDate
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.very_small_padding)))
        }
        item {
            BodyField(
                onBodyUpdate = { onValueChange(noteUiState.noteDetails.copy(body = it)) },
                body = noteUiState.noteDetails.body,
                textFieldCustomColors = textFieldCustomColors,
                modifier = Modifier
                    .fillParentMaxSize()
            )
        }
    }
}

// TODO: Find better ways to implement [TextField]

@Composable
private fun BodyField(
    onBodyUpdate: (String) -> Unit,
    body: String,
    textFieldCustomColors: TextFieldColors,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = body,
        onValueChange = { onBodyUpdate(it) },
        textStyle = MaterialTheme.typography.titleMedium,
        placeholder = {
            Text(
                text = stringResource(R.string.start_typing),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.alpha(0.7f)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        colors = textFieldCustomColors,
        shape = RectangleShape
    )
}

@Composable
private fun TitleField(
    onTitleUpdate: (String) -> Unit,
    title: String,
    textFieldCustomColors: TextFieldColors,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = title,
        onValueChange = { onTitleUpdate(it) },
        textStyle = TextStyle(
            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.alpha(0.7f)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        colors = textFieldCustomColors,
        shape = RectangleShape
    )


    LaunchedEffect(focusRequester) {
        awaitFrame()
        focusRequester.requestFocus()
    }
}

@Composable
fun NoteCharacterCountAndDate(
    modifier: Modifier = Modifier,
    characters: Int,
    dateAndTime: String
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.extra_small_padding))
    ) {
        // Might need to hard code with with spaces to have consistency "  |  "
        Text(
            text = dateAndTime,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.alpha(0.6f)
        )
        VerticalDivider(modifier = Modifier.fillMaxHeight())
        Text(
            text = pluralStringResource(
                id = R.plurals.characters,
                count = characters,
                characters
            ),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.alpha(0.6f)
        )
    }
}

fun shareNote(
    title: String,
    body: String,
    date: String,
    context: Context
) {
    val note: String =
        when {
            title.isBlank() && body.isBlank() -> "No text"
            title.isBlank() -> "$body \n\n $date"
            body.isBlank() -> "$title \n\n $date"
            else -> "$title \n\n $body \n\n $date"
        }

    val sendNoteIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, note)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendNoteIntent, null)

    context.startActivity(shareIntent)
}

package com.shivathapa.khaata.ui.category.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shivathapa.khaata.R
import com.shivathapa.khaata.ui.AppViewModelProvider
import com.shivathapa.khaata.ui.KhaataTopAppBar
import com.shivathapa.khaata.ui.category.CategoryDetails
import com.shivathapa.khaata.ui.category.CategoryUiState
import com.shivathapa.khaata.ui.common.BottomNextCancelButtons
import com.shivathapa.khaata.ui.common.EditableNormalTextField
import com.shivathapa.khaata.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object CategoryEntryDestination : NavigationDestination {
    override val route: String = "category_entry"
    override val titleRes: Int = R.string.add_category
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryEntryScreen(
    onNavigateUp: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: CategoryEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    // The rememberCoroutineScope() is a composable function that returns a CoroutineScope bound to the composition where it's called.
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            KhaataTopAppBar(
                title = stringResource(id = CategoryEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        CategoryEntryBody(
            categoryUiState = viewModel.categoryUiState,
            onCategoryValueChange = viewModel::updateCategoryUiState,
            onSaveClick = {
                  coroutineScope.launch {
                      viewModel.saveCategory()
                      navigateBack()
                  }
            },
            onNavigateUp = onNavigateUp,
            modifier = modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        )
    }
}

@Composable
fun CategoryEntryBody(
    categoryUiState: CategoryUiState,
    onCategoryValueChange: (CategoryDetails) -> Unit,
    onSaveClick: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(0.2f))
        CategoryInputForm(
            categoryDetails = categoryUiState.categoryDetails,
            onCategoryValueChange = onCategoryValueChange,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.weight(1f))
        BottomNextCancelButtons(
            onSaveButtonClicked = onSaveClick,
            saveEnabled = categoryUiState.isEntryValid,
            onCancelButtonClicked = onNavigateUp,
            modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_medium)
            )
        )
    }
}

@Composable
fun CategoryInputForm(
    categoryDetails: CategoryDetails,
    modifier: Modifier = Modifier,
    onCategoryValueChange: (CategoryDetails) -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        CategoryEntryImage()
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
        EditableNormalTextField(
//            placeholder = R.string.category,
            label = R.string.category,
            colors = OutlinedTextFieldDefaults.colors(),
            leadingIcon = Icons.Outlined.ShoppingCart,
            value = categoryDetails.category,
            onValueChanged = { onCategoryValueChange(categoryDetails.copy(category = it)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
            ),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CategoryEntryImage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(dimensionResource(id = R.dimen.category_entry_image_height))
    ) {
        Image(
            painter = painterResource(id = R.drawable.small_farmer_milking_cow),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = Color.Black.copy(alpha = 0.5f), blendMode = BlendMode.Darken
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .clip(MaterialTheme.shapes.small)
        )
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.in_image_icon_size))
                .align(Alignment.Center)
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun CategoryEntryFieldPreview() {
//    KhaataTheme {
//        CategoryEntryBody(
//            onNavigateUp = {}
//        )
//    }
//}
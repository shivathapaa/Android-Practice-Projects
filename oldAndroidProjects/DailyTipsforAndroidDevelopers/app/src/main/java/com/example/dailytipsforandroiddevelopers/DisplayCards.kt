package com.example.dailytipsforandroiddevelopers

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailytipsforandroiddevelopers.model.BonusDataSource
import com.example.dailytipsforandroiddevelopers.model.BonusTips
import com.example.dailytipsforandroiddevelopers.model.Tips
import com.example.dailytipsforandroiddevelopers.model.TipsDataSource
import com.example.dailytipsforandroiddevelopers.ui.theme.DailyTipsForAndroidDevelopersTheme

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun TipsList(
    listOfTips: List<Tips>,
    bonusList: List<BonusTips>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    var visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
//        enter = slideInHorizontally(animationSpec = spring(stiffness = StiffnessVeryLow, dampingRatio = DampingRatioLowBouncy)),
//        exit = slideOutHorizontally()
        enter = EnterTransition.None, //In AnimatedVisibility apply no animations at all so that children can each have their own distinct animations
        exit = ExitTransition.None
    ) {
        LazyColumn(contentPadding = contentPadding) {
            itemsIndexed(listOfTips) { index, tip ->
                TipsCardItems(
                    tips = tip,
                    index = index,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.card_horizontal_padding),
                            vertical = dimensionResource(id = R.dimen.card_vertical_padding)
                        )
//                        .animateItemPlacement()
                        .animateEnterExit(
                            enter = slideInHorizontally(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetX = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
    }

//        LazyRow() {
//            itemsIndexed(bonusList) {index, bonus ->
//                BonusItems(
//                    bonusTips = bonus,
//                    index = index,
//                    modifier = Modifier
//                        .padding(dimensionResource(id = R.dimen.card_vertical_padding))
//                    )
//            }
//        }
    }
//}


//@Composable
//fun BonusItems(
//    bonusTips: BonusTips,
//    index: Int,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
//        modifier = modifier
//    ) {
//        Text(
//            text = (index + 1).toString() + ". " + stringResource(id = bonusTips.bonusPoint),
//            style = MaterialTheme.typography.bodyLarge,
//            modifier = Modifier
//                .padding(dimensionResource(id = R.dimen.card_content)
//                )
//        )
//    }
//}

@Composable
fun TipsCardItems(
    tips: Tips,
    index: Int,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        var expanded by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.card_content),
                    end = dimensionResource(id = R.dimen.card_content),
                    bottom = dimensionResource(id = R.dimen.card_content),
                    top = dimensionResource(id = R.dimen.card_content_top)
                )
                .animateContentSize()
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.tip) + " " + (index + 1),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
                BonusTipDropDown(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            DecorativeImage(imageResource = tips.imageResource)
            TipTitle(titleResource = tips.title)

            if (expanded) ExtraPointRes(pointOne = tips.pointOne, pointTwo = tips.pointTwo)
        }
    }
}

@Composable
fun BonusTipDropDown(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = stringResource(id = R.string.see_subpoints),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun DecorativeImage(@DrawableRes imageResource: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.image_box_top),
                bottom = dimensionResource(id = R.dimen.image_box_bottom)
            )
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
        )
    }
}

@Composable
private fun TipTitle(@StringRes titleResource: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = titleResource),
        style = MaterialTheme.typography.displaySmall,
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.title_bottom))
    )
}

@Composable
private fun ExtraPointRes(@StringRes pointOne: Int, @StringRes pointTwo: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(id = R.string.bullet)+ " " + stringResource(id = pointOne),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(id = R.string.bullet) + " " + stringResource(id = pointTwo),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    val tips = Tips(
        R.string.tip_one,
        R.string.tip_one_point_one,
        R.string.tip_one_point_two,
        R.drawable.image_twentynine
    )
    DailyTipsForAndroidDevelopersTheme {
        TipsCardItems(tips = tips, index = 1)
    }
}

@Preview(showBackground = true)
@Composable
fun TipsPreview() {
    DailyTipsForAndroidDevelopersTheme(darkTheme = false) {
        TipsList(listOfTips = TipsDataSource.listOfTips, bonusList = BonusDataSource.tipBonus)
    }
}
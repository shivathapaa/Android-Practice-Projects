package com.example.dailytipsforandroiddevelopers.model

import com.example.dailytipsforandroiddevelopers.R

object BonusDataSource {
    val tipBonus = listOf<BonusTips>(
        BonusTips(
            bonusPoint = R.string.bonus_tip_one
        ),
        BonusTips(
            bonusPoint = R.string.bonus_tip_two
        ),
        BonusTips(
            bonusPoint = R.string.bonus_tip_three
        ),
        BonusTips(
            bonusPoint = R.string.bonus_tip_four
        ),
        BonusTips(
            bonusPoint = R.string.bonus_tip_five
        )
    )
}

//package com.example.dailytipsforandroiddevelopers.model
//
//import com.example.dailytipsforandroiddevelopers.R
//
//object TipsDataSource {
//    val listOfTips = listOf<Tips>(
//        Tips(
//            title = R.string.tip_one,
//            pointOne = R.string.tip_one_point_one,
//            pointTwo = R.string.tip_one_point_two,
//            imageResource = R.drawable.image_one
//        ),
//        Tips(
//            title =  R.string.tip_two,
//            pointOne = R.string.tip_two_point_one,
//            pointTwo = R.string.tip_two_point_two,
//            imageResource = R.drawable.image_two
//        )
//    )
//
//    val tipListForHorizontalGrid = listOf<Tips>(
//        Tips(
//            title =  R.string.tip_three,
//            pointOne = R.string.tip_three_point_one,
//            pointTwo = R.string.tip_three_point_two,
//            imageResource = R.drawable.image_three
//        ),
//        Tips(
//            title =  R.string.tip_four,
//            pointOne = R.string.tip_four_point_one,
//            pointTwo = R.string.tip_four_point_two,
//            imageResource = R.drawable.image_four
//        ),
//        Tips(
//            title =  R.string.tip_five,
//            pointOne = R.string.tip_five_point_one,
//            pointTwo = R.string.tip_five_point_two,
//            imageResource = R.drawable.image_five
//        ),
//        Tips(
//            title =  R.string.tip_six,
//            pointOne = R.string.tip_six_point_one,
//            pointTwo = R.string.tip_six_point_two,
//            imageResource = R.drawable.image_six
//        ),
//        Tips(
//            title =  R.string.tip_seven,
//            pointOne = R.string.tip_seven_point_one,
//            pointTwo = R.string.tip_seven_point_two,
//            imageResource = R.drawable.image_seven
//        ),
//        Tips(
//            title =  R.string.tip_eight,
//            pointOne = R.string.tip_eight_point_one,
//            pointTwo = R.string.tip_eight_point_two,
//            imageResource = R.drawable.image_eight
//        )
//    )
//
//    val tipListForVerticalGrid = listOf<Tips>(
//        Tips(
//            title =  R.string.tip_nine,
//            pointOne = R.string.tip_nine_point_one,
//            pointTwo = R.string.tip_nine_point_two,
//            imageResource = R.drawable.image_nine
//        ),
//        Tips(
//            title =  R.string.tip_ten,
//            pointOne = R.string.tip_ten_point_one,
//            pointTwo = R.string.tip_ten_point_two,
//            imageResource = R.drawable.image_ten
//        ),
//        Tips(
//            title =  R.string.tip_eleven,
//            pointOne = R.string.tip_eleven_point_one,
//            pointTwo = R.string.tip_eleven_point_two,
//            imageResource = R.drawable.image_eleven
//        ),
//        Tips(
//            title =  R.string.tip_twelve,
//            pointOne = R.string.tip_twelve_point_one,
//            pointTwo = R.string.tip_twelve_point_two,
//            imageResource = R.drawable.image_twelve
//        )
//    )
//
//    val tipHorizontalList = listOf<Tips> (
//        Tips(
//            title =  R.string.tip_thirteen,
//            pointOne = R.string.tip_thirteen_point_one,
//            pointTwo = R.string.tip_thirteen_point_two,
//            imageResource = R.drawable.image_thirteen
//        ),
//        Tips(
//            title =  R.string.tip_fourteen,
//            pointOne = R.string.tip_fourteen_point_one,
//            pointTwo = R.string.tip_fourteen_point_two,
//            imageResource = R.drawable.image_fourteen
//        ),
//        Tips(
//            title =  R.string.tip_fifteen,
//            pointOne = R.string.tip_fifteen_point_one,
//            pointTwo = R.string.tip_fifteen_point_two,
//            imageResource = R.drawable.image_fifteen
//        ),
//        Tips(
//            title =  R.string.tip_sixteen,
//            pointOne = R.string.tip_sixteen_point_one,
//            pointTwo = R.string.tip_sixteen_point_two,
//            imageResource = R.drawable.image_sixteen
//        ),
//        Tips(
//            title =  R.string.tip_seventeen,
//            pointOne = R.string.tip_seventeen_point_one,
//            pointTwo = R.string.tip_seventeen_point_two,
//            imageResource = R.drawable.image_seventeen
//        ),
//        Tips(
//            title =  R.string.tip_eighteen,
//            pointOne = R.string.tip_eighteen_point_one,
//            pointTwo = R.string.tip_eighteen_point_two,
//            imageResource = R.drawable.image_eighteen
//        ),
//        Tips(
//            title =  R.string.tip_nineteen,
//            pointOne = R.string.tip_nineteen_point_one,
//            pointTwo = R.string.tip_nineteen_point_two,
//            imageResource = R.drawable.image_nineteen
//        ),
//        Tips(
//            title =  R.string.tip_twenty,
//            pointOne = R.string.tip_twenty_point_one,
//            pointTwo = R.string.tip_twenty_point_two,
//            imageResource = R.drawable.image_twenty
//        ),
//        Tips(
//            title =  R.string.tip_twenty_one,
//            pointOne = R.string.tip_twenty_one_point_one,
//            pointTwo = R.string.tip_twenty_one_point_two,
//            imageResource = R.drawable.image_twentyone
//        ),
//        Tips(
//            title =  R.string.tip_twenty_two,
//            pointOne = R.string.tip_twenty_two_point_one,
//            pointTwo = R.string.tip_twenty_two_point_two,
//            imageResource = R.drawable.image_twentytwo
//        ),
//        Tips(
//            title =  R.string.tip_twenty_three,
//            pointOne = R.string.tip_twenty_three_point_one,
//            pointTwo = R.string.tip_twenty_three_point_two,
//            imageResource = R.drawable.image_twentythree
//        ),
//        Tips(
//            title =  R.string.tip_twenty_four,
//            pointOne = R.string.tip_twenty_four_point_one,
//            pointTwo = R.string.tip_twenty_four_point_two,
//            imageResource = R.drawable.image_twentyfour
//        ),
//        Tips(
//            title =  R.string.tip_twenty_five,
//            pointOne = R.string.tip_twenty_five_point_one,
//            pointTwo = R.string.tip_twenty_five_point_two,
//            imageResource = R.drawable.image_twentyfive
//        ),
//        Tips(
//            title =  R.string.tip_twenty_six,
//            pointOne = R.string.tip_twenty_six_point_one,
//            pointTwo = R.string.tip_twenty_six_point_two,
//            imageResource = R.drawable.image_twentysix
//        ),
//        Tips(
//            title =  R.string.tip_twenty_seven,
//            pointOne = R.string.tip_twenty_seven_point_one,
//            pointTwo = R.string.tip_twenty_seven_point_two,
//            imageResource = R.drawable.image_twentyseven
//        ),
//        Tips(
//            title =  R.string.tip_twenty_eight,
//            pointOne = R.string.tip_twenty_eight_point_one,
//            pointTwo = R.string.tip_twenty_eight_point_two,
//            imageResource = R.drawable.image_twentyeight
//        ),
//        Tips(
//            title =  R.string.tip_twenty_nine,
//            pointOne = R.string.tip_twenty_nine_point_one,
//            pointTwo = R.string.tip_twenty_nine_point_two,
//            imageResource = R.drawable.image_twentynine
//        ),
//        Tips(
//            title =  R.string.tip_thirty,
//            pointOne = R.string.tip_thirty_point_one,
//            pointTwo = R.string.tip_thirty_point_two,
//            imageResource = R.drawable.image_thirty
//        )
//    )
//
//
//    val tipBonus = listOf<BonusTips>(
//        BonusTips(
//            bonusPoint = R.string.bonus_tip_one
//        ),
//        BonusTips(
//            bonusPoint = R.string.bonus_tip_two
//        ),
//        BonusTips(
//            bonusPoint = R.string.bonus_tip_three
//        ),
//        BonusTips(
//            bonusPoint = R.string.bonus_tip_four
//        ),
//        BonusTips(
//            bonusPoint = R.string.bonus_tip_five
//        )
//    )
//}
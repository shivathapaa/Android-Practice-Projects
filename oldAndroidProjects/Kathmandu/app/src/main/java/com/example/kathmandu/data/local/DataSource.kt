package com.example.kathmandu.data.local

import com.example.kathmandu.R
import com.example.kathmandu.data.ArtGalleryDestination
import com.example.kathmandu.data.CasinoDestination
import com.example.kathmandu.data.MovieDestination
import com.example.kathmandu.data.ResortDestination
import com.example.kathmandu.data.ShoppingMallDestination
import com.example.kathmandu.data.TempleDestination
import com.example.kathmandu.model.DestinationContent

object DataSource {
    val artGalleries = listOf<ArtGalleryDestination>(
        ArtGalleryDestination(
            name = R.string.siddhartha_art_gallery,
            description = R.string.siddhartha_art_gallery_description,
            exhibition = R.string.siddhartha_art_gallery_exhibition,
            time = R.string.siddhartha_art_gallery_timing,
            location = R.string.siddhartha_art_gallery_location
        ),
        ArtGalleryDestination(
            name = R.string.artudio_art_gallery,
            description = R.string.artudio_art_gallery_description,
            exhibition = R.string.artudio_art_gallery_exhibition,
            time = R.string.artudio_art_gallery_timing,
            location = R.string.artudio_art_gallery_location
        ),
        ArtGalleryDestination(
            name = R.string.sarwanam_art_gallery,
            description = R.string.sarwanam_art_gallery_description,
            exhibition = R.string.sarwanam_art_gallery_exhibition,
            time = R.string.sarwanam_art_gallery_timing,
            location = R.string.sarwanam_art_gallery_location
        ),
        ArtGalleryDestination(
            name = R.string.nepal_academy_art_gallery,
            description = R.string.nepal_academy_art_gallery_description,
            exhibition = R.string.nepal_academy_art_gallery_exhibition,
            time = R.string.nepal_academy_art_gallery_timing,
            location = R.string.nepal_academy_art_gallery_location
        ),
        ArtGalleryDestination(
            name = R.string.himalayan_art_gallery,
            description = R.string.himalayan_art_gallery_description,
            exhibition = R.string.himalayan_art_gallery_exhibition,
            time = R.string.himalayan_art_gallery_timing,
            location = R.string.himalayan_art_gallery_location
        ),
        ArtGalleryDestination(
            name = R.string.universal_art_gallery,
            description = R.string.universal_art_gallery_description,
            exhibition = R.string.universal_art_gallery_exhibition,
            time = R.string.universal_art_gallery_timing,
            location = R.string.universal_art_gallery_timing
        ),
        ArtGalleryDestination(
            name = R.string.twentyFirst_century_art_gallery,
            description = R.string.twentyFirst_century_art_gallery_description,
            exhibition = R.string.twentyFirst_century_art_gallery_exhibition,
            time = R.string.twentyFirst_century_art_gallery_timing,
            location = R.string.twentyFirst_century_art_gallery_location
        )
    )

    val casinos = listOf<CasinoDestination>(
        CasinoDestination(
            name = R.string.casino_rad,
            description = R.string.casino_rad_description,
            location = R.string.casino_rad_location,
            time = R.string.casino_rad_timing,
            contact = R.string.casino_rad_contact
        ),
        CasinoDestination(
            name = R.string.casino_anna,
            description = R.string.casino_anna_description,
            location = R.string.casino_anna_location,
            time = R.string.casino_anna_timing,
            contact = R.string.casino_anna_contact
        ),
        CasinoDestination(
            name = R.string.casino_royale,
            description = R.string.casino_royale_description,
            location = R.string.casino_royale_location,
            time = R.string.casino_royale_timing,
            contact = R.string.casino_royale_contact
        ),
        CasinoDestination(
            name = R.string.casino_pride_nepal,
            description = R.string.casino_pride_nepal_description,
            location = R.string.casino_pride_nepal_location,
            time = R.string.casino_pride_nepal_timing,
            contact = R.string.casino_pride_nepal_contact
        ),
        CasinoDestination(
            name = R.string.casino_mahjong,
            description = R.string.casino_mahjong_description,
            location = R.string.casino_mahjong_location,
            time = R.string.casino_mahjong_timing,
            contact = R.string.casino_mahjong_contact
        ),
        CasinoDestination(
            name = R.string.millionaire_club,
            description = R.string.millionaire_club_description,
            location = R.string.millionaire_club_location,
            time = R.string.millionaire_club_timing,
            contact = R.string.millionaire_club_contact
        ),
        CasinoDestination(
            name = R.string.casino_waldo,
            description = R.string.casino_waldo_description,
            location = R.string.casino_waldo_location,
            time = R.string.casino_waldo_timing,
            contact = R.string.casino_waldo_contact
        ),
        CasinoDestination(
            name = R.string.deltin_royale,
            description = R.string.deltin_royale_description,
            location = R.string.deltin_royale_location,
            time = R.string.deltin_royale_timing,
            contact = R.string.deltin_royale_contact
        ),
        CasinoDestination(
            name = R.string.ballyS_casino,
            description = R.string.ballyS_casino_description,
            location = R.string.ballyS_casino_location,
            time = R.string.ballyS_casino_timing,
            contact = R.string.ballyS_casino_contact
        )
    )

    val movieHalls = listOf<MovieDestination>(
        MovieDestination(
            name = R.string.qfx_cinemas,
            description = R.string.qfx_cinemas_description,
            location = R.string.qfx_cinemas_location,
            contact = R.string.qfx_cinemas_contact
        ),
        MovieDestination(
            name = R.string.big_movies,
            description = R.string.big_movies_description,
            location = R.string.big_movies_location,
            contact = R.string.big_movies_contact
        ),
        MovieDestination(
            name = R.string.q_cinemas,
            description = R.string.q_cinemas_description,
            location = R.string.q_cinemas_location,
            contact = R.string.q_cinemas_contact
        ),
        MovieDestination(
            name = R.string.fcube_cinemas,
            description = R.string.fcube_cinemas_description,
            location = R.string.fcube_cinemas_location,
            contact = R.string.fcube_cinemas_contact
        ),
        MovieDestination(
            name = R.string.cine_de_chef_cinemas,
            description = R.string.cine_de_chef_cinemas_description,
            location = R.string.cine_de_chef_cinemas_location,
            contact = R.string.cine_de_chef_cinemas_contact
        ),
        MovieDestination(
            name = R.string.gopiKrishna_cinema,
            description = R.string.gopiKrishna_cinema_description,
            location = R.string.gopiKrishna_cinema_location,
            contact = R.string.gopiKrishna_cinema_contact
        ),
        MovieDestination(
            name = R.string.bsr_movies,
            description = R.string.bsr_movies_description,
            location = R.string.bsr_movies_location,
            contact = R.string.bsr_movies_contact
        ),
        MovieDestination(
            name = R.string.ini_north_cinemas,
            description = R.string.ini_north_cinemas_description,
            location = R.string.ini_north_cinemas_location,
            contact = R.string.ini_north_cinemas_contact
        )
    )

    val insideValleyResorts = listOf<ResortDestination>(
        ResortDestination(
            name = R.string.gokarna_forest_resort,
            description = R.string.gopiKrishna_cinema_description,
            rating = R.string.gokarna_forest_resort_rating,
            location = R.string.gopiKrishna_cinema_location,
            features = R.string.gokarna_forest_resort_features
        ),
        ResortDestination(
            name = R.string.kankali_viewpoint_resort,
            description = R.string.kankali_viewpoint_resort_description,
            rating = R.string.kankali_viewpoint_resort_rating,
            location = R.string.kankali_viewpoint_resort_location,
            features = R.string.kankali_viewpoint_resort_features
        ),
        ResortDestination(
            name = R.string.thamel_eco_resort,
            description = R.string.thamel_eco_resort_description,
            rating = R.string.thamel_eco_resort_rating,
            location = R.string.thamel_eco_resort_location,
            features = R.string.thamel_eco_resort_features
        ),
        ResortDestination(
            name = R.string.dhulikhel_mountain_resort,
            description = R.string.dhulikhel_mountain_resort_description,
            rating = R.string.dhulikhel_mountain_resort_rating,
            location = R.string.dhulikhel_mountain_resort_location,
            features = R.string.dhulikhel_mountain_resort_features
        ),
        ResortDestination(
            name = R.string.nepal_cottage_resort,
            description = R.string.nepal_cottage_resort_description,
            rating = R.string.nepal_cottage_resort_rating,
            location = R.string.nepal_cottage_resort_location,
            features = R.string.nepal_cottage_resort_features
        ),
        ResortDestination(
            name = R.string.dwarika_resort,
            description = R.string.dwarika_resort_description,
            rating = R.string.dwarika_resort_rating,
            location = R.string.dwarika_resort_location,
            features = R.string.dwarika_resort_features
        )
    )

    val outsideValleyResorts = listOf<ResortDestination>(
        ResortDestination(
            name = R.string.green_eco_resort,
            description = R.string.green_eco_resort_description,
            rating = R.string.green_eco_resort_rating,
            location = R.string.green_eco_resort_location,
            features = R.string.green_eco_resort_features
        ),
        ResortDestination(
            name = R.string.taj_riverside_resort,
            description = R.string.taj_riverside_resort_description,
            rating = R.string.taj_riverside_resort_rating,
            location = R.string.taj_riverside_resort_location,
            features = R.string.taj_riverside_resort_features
        )
    )

    val shoppingMalls = listOf<DestinationContent>(
        DestinationContent(
            name = R.string.city_center,
            intro = R.string.city_center_intro,
            description = R.string.city_center_description,
            highlights = R.string.city_center_highlights,
            location = R.string.city_center_location
        ),
        DestinationContent(
            name = R.string.civil_mall,
            intro = R.string.civil_mall_intro,
            description = R.string.civil_mall_description,
            highlights = R.string.civil_mall_highlights,
            location = R.string.civil_mall_location
        ),
        DestinationContent(
            name = R.string.kl_tower_and_multicomplex,
            intro = R.string.kl_tower_and_multicomplex_intro,
            description = R.string.kl_tower_and_multicomplex_description,
            highlights = R.string.kl_tower_and_multicomplex_highlights,
            location = R.string.kl_tower_and_multicomplex_location
        ),
        ShoppingMallDestination(
            name = R.string.civil_trade_centre_mall,
            intro = R.string.civil_trade_centre_intro,
            description = null,
            highlights = R.string.civil_trade_centre_highlights,
            location = R.string.civil_trade_centre_location
        ),
        ShoppingMallDestination(
            name = R.string.peoples_plaza,
            intro = R.string.peoples_plaza_intro,
            description = null,
            highlights = R.string.peoples_plaza_highlights,
            location = R.string.peoples_plaza_location
        ),
        ShoppingMallDestination(
            name = R.string.sherpa_mall,
            intro = R.string.sherpa_mall_intro,
            description = null,
            highlights = R.string.sherpa_mall_highlights,
            location = R.string.sherpa_mall_location
        ),
        ShoppingMallDestination(
            name = R.string.labim_mall,
            intro = R.string.labim_mall_intro,
            description = R.string.labim_mall_description,
            highlights = R.string.labim_mall_highlights,
            location = R.string.labim_mall_location
        ),
        ShoppingMallDestination(
            name = R.string.united_world_trade_center,
            intro = R.string.united_world_trade_center_intro,
            description = R.string.united_world_trade_center_description,
            highlights = R.string.united_world_trade_center_highlights,
            location = R.string.united_world_trade_center_location
        ),
        ShoppingMallDestination(
            name = R.string.rising_mall,
            intro = R.string.rising_mall_intro,
            description = R.string.rising_mall_description,
            highlights = R.string.rising_mall_highlights,
            location = R.string.rising_mall_location
        ),
        ShoppingMallDestination(
            name = R.string.times_square_mall,
            intro = R.string.times_square_mall_intro,
            description = R.string.times_square_mall_description,
            highlights = R.string.times_square_mall_highlights,
            location = R.string.times_square_mall_location
        )
    )


    val insideValleyTemples = listOf<TempleDestination>(
        TempleDestination(
            name = R.string.pashupatinath_temple,
            description = R.string.pashupatinath_temple_description,
            details = R.string.pashupatinath_temple_details,
        ),
        TempleDestination(
            name = R.string.swoyambhunath_stupa,
            description = R.string.swoyambhunath_stupa_description,
            details = R.string.swoyambhunath_stupa_details,
        ),
        TempleDestination(
            name = R.string.krishna_mandir,
            description = R.string.krishna_mandir_description,
            details = R.string.krishna_mandir_details,
        ),
        TempleDestination(
            name = R.string.nyatapola_temple,
            description = R.string.nyatapola_temple_decription,
            details = R.string.nyatapola_temple_decription,
        ),
        TempleDestination(
            name = R.string.changunarayan_temple,
            description = R.string.changunarayan_temple_description,
            details = R.string.changunaraya_temple_details,
        ),
        TempleDestination(
            name = R.string.dattatreya_temple,
            description = R.string.dattatreya_temple_description,
            details = R.string.dattatreya_temple_details,
        ),
        TempleDestination(
            name = R.string.chandeshwori_temple,
            description = R.string.chandeshwori_temple_description,
            details = R.string.changunaraya_temple_details,
        ),
        TempleDestination(
            name = R.string.budhanilkantha_temple,
            description = R.string.budhanilkantha_temple_description,
            details = R.string.budhanilkanthan_temple_details,
        )
    )

    val outsideValleyTemples = listOf<TempleDestination>(
        TempleDestination(
            name = R.string.janaki_temple,
            description = R.string.janaki_temple_description,
            details = R.string.janaki_temple_details,
        ),
        TempleDestination(
            name = R.string.manakamana_temple,
            description = R.string.manakamana_temple_description,
            details = null,
        ),
        TempleDestination(
            name = R.string.talbarahi_temple,
            description = R.string.talbarahi_temple_description,
            details = null,
        )
    )
}
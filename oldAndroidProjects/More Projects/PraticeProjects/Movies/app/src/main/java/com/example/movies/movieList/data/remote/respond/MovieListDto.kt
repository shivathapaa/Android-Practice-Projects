package com.example.movies.movieList.data.remote.respond

data class MovieListDto(
//    val dates: MovieDates,
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)
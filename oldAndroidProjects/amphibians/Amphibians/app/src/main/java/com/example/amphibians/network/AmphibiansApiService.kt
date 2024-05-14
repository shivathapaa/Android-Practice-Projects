package com.example.amphibians.network

import com.example.amphibians.model.AmphibiansDetails
import retrofit2.http.GET

interface AmphibiansApiService {
    /**
     * Returns a [List] of [AmphibiansDetails] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "amphibians" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("amphibians")
    suspend fun getAmphibiansDetails(): List<AmphibiansDetails>
}
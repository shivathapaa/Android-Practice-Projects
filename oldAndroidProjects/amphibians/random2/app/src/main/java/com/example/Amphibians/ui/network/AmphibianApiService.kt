package com.example.Amphibians.ui.network

import com.example.Amphibians.ui.model.Amphibian
import retrofit2.http.GET

interface AmphibianApiService {
    // Use the @GET annotation to tell Retrofit that this is a GET request and specify an endpoint for that web service method
    @GET("amphibians")
    suspend fun getAmphibiansDetail(): List<Amphibian>
}
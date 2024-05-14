package com.example.amphibians.data

import com.example.amphibians.model.AmphibiansDetails
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibiansDetails(): List<AmphibiansDetails>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibiansDetails(): List<AmphibiansDetails> = amphibiansApiService.getAmphibiansDetails()
}
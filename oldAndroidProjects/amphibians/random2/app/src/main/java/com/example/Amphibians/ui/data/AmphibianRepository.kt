package com.example.Amphibians.ui.data

import com.example.Amphibians.ui.model.Amphibian
import com.example.Amphibians.ui.network.AmphibianApiService

interface AmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibianRepository(
    private val amphibianApiService: AmphibianApiService
) : AmphibianRepository {
    override suspend fun getAmphibians(): List<Amphibian> = amphibianApiService.getAmphibiansDetail()
}
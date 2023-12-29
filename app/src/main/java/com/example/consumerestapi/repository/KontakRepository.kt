package com.example.consumerestapi.repository

import com.example.consumerestapi.model.Kontak
import com.example.consumerestapi.service_api.KontakService

interface KontakRepository {
    suspend fun getKontak(): List<Kontak>
}

class NetworkKontakRepository(
    private val kontakApiService: KontakService
) : KontakRepository {
    override suspend fun getKontak(): List<Kontak> = kontakApiService.getKontak()
}
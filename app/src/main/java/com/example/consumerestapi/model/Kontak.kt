package com.example.consumerestapi.model

import kotlinx.serialization.Serializable

@Serializable
data class Kontak(
    val id: Int,
    val name: String,
    val alamat: String,
    val telpon: String,
)

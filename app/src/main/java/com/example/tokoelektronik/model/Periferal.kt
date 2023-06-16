package com.example.tokoelektronik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Periferal(
    @PrimaryKey val id: String,
    val nama: String,
    val harga: String,
    val deskripsi: String,
    val jenis: String

)
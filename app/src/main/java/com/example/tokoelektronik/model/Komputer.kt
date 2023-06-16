package com.example.tokoelektronik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Komputer (
    @PrimaryKey val id: String,
    val merk: String,
    val jenis: String,
    val harga: String,
    val dapat_diupgrade: String,
    val spesifikasi : String
)


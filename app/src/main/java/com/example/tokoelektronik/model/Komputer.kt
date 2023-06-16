package com.example.tokoelektronik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Komputer (
    @PrimaryKey val id: String,
    val merk: String,
    val jenis: String,
    val harga: Int,
    val dapat_diupgrade: Boolean,
    val spesifikasi : String
)


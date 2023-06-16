package com.example.tokoelektronik.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tokoelektronik.model.Komputer
import com.example.tokoelektronik.model.Periferal
import com.example.tokoelektronik.model.Smartphone

@Database(entities = [Smartphone::class, Komputer::class, Periferal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
//    komputer
    abstract fun komputerDao(): KomputerDao
//    periferal
    abstract fun periferalDao(): PeriferalDao
//    smartphone
    abstract fun smartphoneDao(): SmartphoneDao

}
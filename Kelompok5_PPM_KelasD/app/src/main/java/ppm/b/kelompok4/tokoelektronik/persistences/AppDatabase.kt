package ppm.b.kelompok4.tokoelektronik.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import ppm.b.kelompok4.tokoelektronik.model.Komputer
import ppm.b.kelompok4.tokoelektronik.model.Periferal
import ppm.b.kelompok4.tokoelektronik.model.Smartphone

@Database(entities = [Komputer::class, Periferal::class, Smartphone::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun komputerDao(): KomputerDao
    abstract fun periferalDao(): PeriferalDao
    abstract fun smartphoneDao(): SmartphoneDao
}
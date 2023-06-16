package ppm.b.kelompok4.tokoelektronik.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ppm.b.kelompok4.tokoelektronik.persistences.AppDatabase
import ppm.b.kelompok4.tokoelektronik.persistences.KomputerDao
import ppm.b.kelompok4.tokoelektronik.persistences.PeriferalDao
import ppm.b.kelompok4.tokoelektronik.persistences.SmartphoneDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "toko-elektronik"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideKomputerDao(appDatabase: AppDatabase): KomputerDao {
        return appDatabase.komputerDao()
    }
    @Provides
    @Singleton
    fun providePeriferalDao(appDatabase: AppDatabase): PeriferalDao {
        return appDatabase.periferalDao()
    }
    @Provides
    @Singleton
    fun provideSmartphoneDao(appDatabase: AppDatabase): SmartphoneDao {
        return appDatabase.smartphoneDao()
    }
}
package com.example.TokoElektronik.di

import android.app.Application
import androidx.room.Room
import com.example.TokoElektronik.persistences.AppDatabase
import com.example.TokoElektronik.persistences.KomputerDao
import com.example.TokoElektronik.persistences.PeriferalDao
import com.example.TokoElektronik.persistences.SmartphoneDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
                "tokoelektronik"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
//    komputer
    @Provides
    @Singleton
    fun provideKkomputerDao(appDatabase: AppDatabase): KomputerDao {
        return appDatabase.komputerDao()
    }

//    periferal
    @Provides
    @Singleton
    fun providePeriferalDao(appDatabase: AppDatabase): PeriferalDao {
        return appDatabase.periferalDao()
    }
//    smartphone
    @Provides
    @Singleton
    fun provideSmartphoneDao(appDatabase: AppDatabase): SmartphoneDao {
        return appDatabase.smartphoneDao()
    }
}
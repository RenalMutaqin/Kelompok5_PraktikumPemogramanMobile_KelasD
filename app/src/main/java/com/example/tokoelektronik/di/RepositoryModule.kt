package com.example.TokoElektronik.di

import com.example.TokoElektronik.networks.KomputerApi
import com.example.TokoElektronik.networks.PeriferalApi
import com.example.TokoElektronik.networks.SmartphoneApi
import com.example.TokoElektronik.persistences.KomputerDao
import com.example.TokoElektronik.persistences.PeriferalDao
import com.example.TokoElektronik.persistences.SmartphoneDao
import com.example.TokoElektronik.repositories.KomputerRepository
import com.example.TokoElektronik.repositories.PeriferalRepository
import com.example.TokoElektronik.repositories.SmartphoneRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
//    komputer
    @Provides
    @ViewModelScoped
    fun provideKomputerRepository(
        api: KomputerApi,
        dao: KomputerDao
    ): KomputerRepository {
        return KomputerRepository(api, dao)
    }

//    periferal
    @Provides
    @ViewModelScoped
    fun providePeriferalRepository(
        api: PeriferalApi,
        dao: PeriferalDao
    ): PeriferalRepository {
        return PeriferalRepository(api, dao)
    }

//    smartphone
    @Provides
    @ViewModelScoped
    fun provideSmartphoneRepository(
        api: SmartphoneApi,
        dao: SmartphoneDao
    ): SmartphoneRepository {
        return SmartphoneRepository(api, dao)
    }
}
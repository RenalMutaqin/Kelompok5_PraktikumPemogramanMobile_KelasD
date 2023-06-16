package com.example.tokoelektronik.di

import com.example.tokoelektronik.networks.KomputerApi
import com.example.tokoelektronik.networks.PeriferalApi
import com.example.tokoelektronik.networks.SmartphoneApi
import com.example.tokoelektronik.persistences.KomputerDao
import com.example.tokoelektronik.persistences.PeriferalDao
import com.example.tokoelektronik.persistences.SmartphoneDao
import com.example.tokoelektronik.repositories.KomputerRepository
import com.example.tokoelektronik.repositories.PeriferalRepository
import com.example.tokoelektronik.repositories.SmartphoneRepository
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
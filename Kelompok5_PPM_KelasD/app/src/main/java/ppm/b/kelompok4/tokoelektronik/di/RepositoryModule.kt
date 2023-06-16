package ppm.b.kelompok4.tokoelektronik.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ppm.b.kelompok4.tokoelektronik.networks.KomputerApi
import ppm.b.kelompok4.tokoelektronik.networks.PeriferalApi
import ppm.b.kelompok4.tokoelektronik.networks.SmartphoneApi
import ppm.b.kelompok4.tokoelektronik.persistences.KomputerDao
import ppm.b.kelompok4.tokoelektronik.persistences.PeriferalDao
import ppm.b.kelompok4.tokoelektronik.persistences.SmartphoneDao
import ppm.b.kelompok4.tokoelektronik.repositories.KomputerRepository
import ppm.b.kelompok4.tokoelektronik.repositories.PeriferalRepository
import ppm.b.kelompok4.tokoelektronik.repositories.SmartphoneRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideKomputerRepository(
        api: KomputerApi,
        dao: KomputerDao
    ): KomputerRepository {
        return KomputerRepository(api, dao)
    }
    @Provides
    @ViewModelScoped
    fun providePeriferalRepository(
        api: PeriferalApi,
        dao: PeriferalDao
    ): PeriferalRepository {
        return PeriferalRepository(api, dao)
    }
    @Provides
    @ViewModelScoped
    fun provideSmartphoneRepository(
        api: SmartphoneApi,
        dao: SmartphoneDao
    ): SmartphoneRepository {
        return SmartphoneRepository(api, dao)
    }
}
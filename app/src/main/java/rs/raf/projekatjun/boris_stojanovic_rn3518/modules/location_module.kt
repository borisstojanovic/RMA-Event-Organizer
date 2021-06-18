package rs.raf.projekatjun.boris_stojanovic_rn3518.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.local.EventDataBase
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.remote.LocationService
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories.EventRepository
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories.EventRepositoryImpl
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories.LocationRepository
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories.LocationRepositoryImpl
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.viewmodel.MainViewModel

val locationModule = module {

    single<LocationRepository> { LocationRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<EventDataBase>().getLocationDao() }

    single<LocationService> { create(retrofit = get()) }

}


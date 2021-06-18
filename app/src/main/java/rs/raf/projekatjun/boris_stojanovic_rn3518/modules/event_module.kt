package rs.raf.projekatjun.boris_stojanovic_rn3518.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.datasources.local.EventDataBase
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories.EventRepository
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories.EventRepositoryImpl
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.viewmodel.EventListViewModel
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.viewmodel.MainViewModel

val eventModule = module {

    viewModel { MainViewModel(eventRepository = get(), locationRepository = get()) }

    viewModel { EventListViewModel(eventRepository = get()) }

    single<EventRepository> { EventRepositoryImpl(localDataSource = get()) }

    single { get<EventDataBase>().getEventDao() }

}


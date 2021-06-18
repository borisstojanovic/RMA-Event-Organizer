package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.Resource
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories.EventRepository
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories.LocationRepository
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.contract.MainContract
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.AddEventState
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.LocationState
import timber.log.Timber

class MainViewModel(
    private val eventRepository: EventRepository,
    private val locationRepository: LocationRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val addDone: MutableLiveData<AddEventState> = MutableLiveData()
    override val locationState: MutableLiveData<LocationState> = MutableLiveData()

    override fun fetch(city: String) {
        val subscription = locationRepository
                .fetch(city)
                .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            when(it) {
                                is Resource.Loading -> locationState.value = LocationState.Loading
                                is Resource.Success -> locationState.value = LocationState.DataFetched
                                is Resource.Error -> locationState.value = LocationState.Error("Error happened while fetching data from the server")
                            }
                        },
                        {
                            locationState.value = LocationState.Error("Error happened while fetching data from the server")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun get(timezone: String) {
        val subscription = locationRepository
                .getByTimezone(timezone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            locationState.value = LocationState.Success(it)
                        },
                        {
                            locationState.value = LocationState.Error("Error happened while fetching data from db")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun addEvent(event: Event) {
        val subscription = eventRepository
            .insert(event)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddEventState.Success
                },
                {
                    addDone.value = AddEventState.Error("Error happened while adding event")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}
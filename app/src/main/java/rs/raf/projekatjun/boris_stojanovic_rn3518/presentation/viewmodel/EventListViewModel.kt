package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.repositories.EventRepository
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.contract.EventContract
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.EventsState
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.RemoveEventState
import timber.log.Timber

class EventListViewModel(
    private val eventRepository: EventRepository,
) : ViewModel(), EventContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val eventsState: MutableLiveData<EventsState> = MutableLiveData()
    override val removeDone: MutableLiveData<RemoveEventState> = MutableLiveData()

    override fun getAll() {
        val subscription = eventRepository
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            eventsState.value = EventsState.Success(it)
                        },
                        {
                            eventsState.value = EventsState.Error("Error happened while fetching data from db")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun remove(event: Event) {
        val subscription = eventRepository
            .delete(event)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    removeDone.value = RemoveEventState.Success
                },
                {
                    removeDone.value = RemoveEventState.Error("Error happened while removing event")
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
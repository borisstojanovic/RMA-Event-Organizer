package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.EventsState
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.RemoveEventState

interface EventContract {

    interface ViewModel {

        val eventsState: LiveData<EventsState>
        val removeDone: LiveData<RemoveEventState>

        fun getAll()
        fun remove(event: Event)
    }

}
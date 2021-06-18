package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.AddEventState
import rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states.LocationState

interface MainContract {

    interface ViewModel {
        val locationState: LiveData<LocationState>
        val addDone: LiveData<AddEventState>

        fun fetch(city: String)
        fun get(timezone: String)
        fun addEvent(event: Event)
    }

}
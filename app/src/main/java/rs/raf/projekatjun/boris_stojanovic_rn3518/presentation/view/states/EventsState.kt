package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states

import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.event.Event

sealed class EventsState {
    data class Success(val events: List<Event>): EventsState()
    data class Error(val message: String): EventsState()
}
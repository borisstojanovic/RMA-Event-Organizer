package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states

import rs.raf.projekatjun.boris_stojanovic_rn3518.data.models.location.Location

sealed class LocationState {
    object Loading: LocationState()
    object DataFetched: LocationState()
    data class Success(val location: Location): LocationState()
    data class Error(val message: String): LocationState()
}
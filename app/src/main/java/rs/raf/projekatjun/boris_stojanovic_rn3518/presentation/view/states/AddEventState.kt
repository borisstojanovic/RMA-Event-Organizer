package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states

sealed class AddEventState {
    object Success: AddEventState()
    data class Error(val message: String): AddEventState()
}
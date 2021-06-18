package rs.raf.projekatjun.boris_stojanovic_rn3518.presentation.view.states

sealed class RemoveEventState {
    object Success: RemoveEventState()
    data class Error(val message: String): RemoveEventState()
}
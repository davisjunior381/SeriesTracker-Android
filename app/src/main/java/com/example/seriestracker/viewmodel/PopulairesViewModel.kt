package com.example.seriestracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriestracker.data.repository.TvShowRepository
import com.example.seriestracker.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// L'état de l'UI — tout ce dont EcranAccueil a besoin
data class UiState(
    val shows: List<TvShow> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class PopulairesViewModel @Inject constructor(
    private val repository: TvShowRepository
) : ViewModel() {

    // Privé en écriture, public en lecture seule → l'UI ne peut pas modifier l'état directement
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        // Chargement automatique au démarrage
        chargerSeries()
    }

    fun chargerSeries() {
        viewModelScope.launch {
            // 1. On signale le chargement
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                // 2. On appelle le repository (suspend → coroutine)
                val series = repository.getPopularShows()
                // 3. Succès : on met les données, on arrête le loading
                _uiState.update { it.copy(shows = series, isLoading = false) }
            } catch (e: Exception) {
                // 4. Erreur : on stocke le message, on arrête le loading
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Erreur inconnue"
                    )
                }
            }
        }
    }

    // Appelée par le bouton "Réessayer" dans l'UI
    fun reessayer() {
        chargerSeries()
    }
}
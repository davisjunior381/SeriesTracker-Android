package com.example.seriestracker.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.seriestracker.viewmodel.PopulairesViewModel

@Composable
fun EcranAccueil(
    viewModel: PopulairesViewModel = hiltViewModel()  // Hilt injecte le ViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        // État CHARGEMENT
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        // État ERREUR
        uiState.errorMessage != null -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Erreur : ${uiState.errorMessage}")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.reessayer() }) {
                    Text("Réessayer")
                }
            }
        }

        // État SUCCÈS
        else -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.shows) { show ->
                    CarteSerie(show = show)
                }
            }
        }
    }
}
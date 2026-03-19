package com.example.seriestracker.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.seriestracker.model.TvShow

@Composable
fun CarteSerie(show: TvShow) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {

            // Miniature chargée avec Coil
            AsyncImage(
                model = show.imageUrl,
                contentDescription = show.name,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Titre
                Text(
                    text = show.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Chaîne et pays
                Text(
                    text = "${show.network} · ${show.country}",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Badge statut
                BadgeStatut(status = show.status)
            }
        }
    }
}

@Composable
fun BadgeStatut(status: String) {
    val (couleur, texte) = when (status.lowercase()) {
        "running" -> Color(0xFF2E7D32) to "En cours"  // vert foncé
        else -> Color(0xFF757575) to "Terminée"        // gris
    }

    Surface(
        color = couleur,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = texte,
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}
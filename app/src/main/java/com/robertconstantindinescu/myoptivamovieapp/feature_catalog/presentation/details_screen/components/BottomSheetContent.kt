package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.LocalSpacing
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.ExtendedTrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.util.Constants.ABOUT_TEXT_LINES
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.LARGE_PADDING
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MEDIUM_PADDING
import java.util.concurrent.TimeUnit

@Composable
fun BottomSheetContent(
    movie: ExtendedTrackableMovie,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.onSurface
) {
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .background(backgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = movie.name,
                style = MaterialTheme.typography.h5,
                color = Color.White.copy(alpha = 0.7f)
            )
            Text(
                text = movie.year.toString(),
                style = MaterialTheme.typography.h5,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Text(
            text = stringResource(id = R.string.about),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary.copy(alpha = 0.8f)

        )
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Text(
            text = movie.description,
            style = MaterialTheme.typography.h6,
            maxLines = ABOUT_TEXT_LINES,
            color = Color.White.copy(alpha = 0.7f)


        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(bottom = 60.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(text = stringResource(id = R.string.other),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Text(
                    text = "Proveedor: ${movie.contentProvider}",
                    style = MaterialTheme.typography.h6,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Text(
                    text = "Duración: ${TimeUnit.MILLISECONDS.toMinutes(movie.duration.toLong())} min",
                    style = MaterialTheme.typography.h6,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Text(
                    text = "Calidad: ${movie.definition}",
                    style = MaterialTheme.typography.h6,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
            Column() {
                Text(text = stringResource(id = R.string.genre),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                movie.genreEntityList.forEachIndexed { index, genreEntity ->
                    Text(
                        text = "${index + 1}. ${genreEntity.name}",
                        style = MaterialTheme.typography.h6,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

            }

        }
    }

}





































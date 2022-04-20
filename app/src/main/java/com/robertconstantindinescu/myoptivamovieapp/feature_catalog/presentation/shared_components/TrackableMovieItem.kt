package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.shared_components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.LocalSpacing
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.catalog_screen.CatalogScreenState
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.util.Constants.BASE_IMAGE_URL
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.util.Constants.IMAGE_NAME
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.IconSizeLarge
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.LARGE_PADDING
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MEDIUM_PADDING
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MOVIE_ITEM_HEIGHT


@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackableMovieItem(
    modifier: Modifier = Modifier,
    trackableMovie: TrackableMovie,
    onFavoriteToggle: () -> Unit = {},
    onTrackableMovieClick: () -> Unit = {},
    isAddedToFav: Boolean = false

) {

    val spacing = LocalSpacing.current
    //Get image url
    val imageUrl = trackableMovie.attachments.find {
        it.name == IMAGE_NAME
    }.run {
        this?.let {
            this.value
        }
    }

    Log.d("BASE_IMAGE_URL", "$BASE_IMAGE_URL${imageUrl}")


    val painter = rememberImagePainter(data = "$BASE_IMAGE_URL${imageUrl}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(modifier = modifier
        .clickable {
            onTrackableMovieClick()
        }
    ) {
        IconButton(
            onClick = { onFavoriteToggle() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(IconSizeLarge)
        ) {
            Icon(
                imageVector =
                Icons.Default.Favorite,
                contentDescription = stringResource(id = R.string.ic_favorite),
                tint = if (isAddedToFav) {
                    MaterialTheme.colors.primary
                } else {
                    MaterialTheme.colors.surface
                }
            )

        }

        Surface(
            shape = RoundedCornerShape(size = LARGE_PADDING),
            modifier = Modifier
                .fillMaxHeight()
        ) {

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(id = R.string.movie_image),
                contentScale = ContentScale.Crop
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            color = Color.Black,
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MEDIUM_PADDING)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        text = trackableMovie.name?:"",
                        style = MaterialTheme.typography.h6,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceMedium))
                    Text(
                        text = trackableMovie.year.toString(),
                        style = MaterialTheme.typography.h6,
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = trackableMovie.description?:"",
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,

                        )
                }


            }

        }
    }

}































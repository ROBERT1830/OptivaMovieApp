package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.favorites_screen.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.LocalSpacing
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackableMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.domain.model.TrackedMovie
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.util.Constants
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.IconSizeLarge
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.LARGE_PADDING
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MEDIUM_PADDING

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FavoriteMovieItem(
    modifier: Modifier = Modifier,
    trackedMovie: TrackedMovie,
    onFavoriteMovieClick: () -> Unit = {},
    onDeleteFavoriteMovieClick: () -> Unit = {}
) {

    val spacing = LocalSpacing.current
    // TODO: EXTRACT AS A USE CASE BECAUSE IS BUSINESS LOGIC
    //Get image url
    val imageUrl = trackedMovie.attachments.find {
        it.name == Constants.IMAGE_NAME
    }.run {
        this?.let {
            this.value
        }
    }

    Log.d("BASE_IMAGE_URL", "${Constants.BASE_IMAGE_URL}${imageUrl}")


    val painter = rememberImagePainter(data = "${Constants.BASE_IMAGE_URL}${imageUrl}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(modifier = modifier
        .clickable {
            onFavoriteMovieClick()
        }
    ) {
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
                        text = trackedMovie.name ?: "",
                        style = MaterialTheme.typography.h6,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceMedium))
                    Text(
                        text = trackedMovie.year.toString(),
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
                        text = trackedMovie.description?:"",
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,

                        )
                }


            }

        }
        IconButton(
            onClick = { onDeleteFavoriteMovieClick() },
            modifier = Modifier
                .padding(all = spacing.spaceMedium)
                .align(Alignment.TopEnd)
                .size(IconSizeLarge)

        ) {
            Image(
                modifier = Modifier.size(IconSizeLarge),
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = stringResource(
                    id = R.string.ic_delete
                )
            )

        }
    }
}
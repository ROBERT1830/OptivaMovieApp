package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.rememberImagePainter
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.util.Constants.BASE_IMAGE_URL
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.util.Constants.MIN_BACKGROUND_IMAGE
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.INFO_ICON_SIZE
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.SMALL_PADDING

@Composable
fun BackgroundContent(
    movieImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClick: () -> Unit
) {

    val imageUrl = "$BASE_IMAGE_URL${movieImage}"
    val painter = rememberImagePainter(imageUrl){
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_BACKGROUND_IMAGE)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.movie_image),
            contentScale = ContentScale.Crop
        )

        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClick() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = Color.White
                )
            }

        }
    }
}
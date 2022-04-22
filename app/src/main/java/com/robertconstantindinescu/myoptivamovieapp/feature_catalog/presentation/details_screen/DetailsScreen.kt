package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen.components.BackgroundContent
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.details_screen.components.BottomSheetContent
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.util.Constants
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.EXPANDED_RADIUS_LEVEL
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.EXTRA_LARGE_PADDING
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.MIN_SHEET_HEIGHT

@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {

    val state = viewModel.state

    //remember bottom sheet state
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )
    val currentSheetFraction = scaffoldState.currentSheetFraction
    val radiusAnim by animateDpAsState(
        targetValue = if (currentSheetFraction == 1f){
            EXTRA_LARGE_PADDING
        }else{
            EXPANDED_RADIUS_LEVEL
        }
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            state.extendedTrackableMovie?.let {
                BottomSheetContent(
                    movie = state.extendedTrackableMovie
                )
            }
        },
        content = {
            state.extendedTrackableMovie?.let {

                // TODO: CONVERT IT INTO A USE CASE
                val imageUrl = state.extendedTrackableMovie.attachments.find {
                    it.name == Constants.IMAGE_NAME
                }.run {
                    this?.let {
                        this.value
                    }
                }

                BackgroundContent(
                    movieImage = imageUrl?:"",
                    imageFraction = currentSheetFraction
                ) {
                    onNavigateUp()
                }
            }
        }

    )


}


@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        //fraction of the bottomSheetState
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        //change value according to last anchor
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }
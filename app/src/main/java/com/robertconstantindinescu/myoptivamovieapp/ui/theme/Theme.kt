package com.robertconstantindinescu.myoptivamovieapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.Dimensions
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.core.util.LocalSpacing

private val DarkColorPalette = darkColors(
    primary = GreenAccent,
    primaryVariant = Purple700,
    background = DarkGray,
    //background for some things like cards
    onBackground = TextWhite,
    onPrimary = DarkGray,
    surface = MediumGray,
    onSurface = LightGray
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MyOptivaMovieAppTheme(
    //darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }

    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colors = DarkColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }

}
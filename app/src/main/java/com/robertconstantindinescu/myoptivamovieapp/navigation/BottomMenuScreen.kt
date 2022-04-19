package com.robertconstantindinescu.myoptivamovieapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.navigation.util.Constants.CATALOG
import com.robertconstantindinescu.myoptivamovieapp.navigation.util.Constants.CATALOG_SCREEN
import com.robertconstantindinescu.myoptivamovieapp.navigation.util.Constants.FAVORITES
import com.robertconstantindinescu.myoptivamovieapp.navigation.util.Constants.FAVORITES_SCREEN

sealed class BottomMenuScreen(val route: String, val icon: ImageVector, val title: String) {
    object Catalog : BottomMenuScreen(
        CATALOG_SCREEN, icon = Icons.Default.List, title = CATALOG
    )

    object Favorites : BottomMenuScreen(
        FAVORITES_SCREEN, icon = Icons.Default.Favorite, title = FAVORITES
    )
}

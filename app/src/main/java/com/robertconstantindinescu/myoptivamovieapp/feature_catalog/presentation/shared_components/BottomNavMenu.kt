package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.shared_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.robertconstantindinescu.myoptivamovieapp.navigation.BottomMenuScreen
import com.robertconstantindinescu.myoptivamovieapp.ui.theme.HintGray

@Composable
fun BottomNavMenu(
    navController: NavController,
    onBottomIconClick: (route:String) -> Unit = {},
    selectedColor: Color = MaterialTheme.colors.primary,
    unselectedColor :Color = HintGray
) {
    val menuItems = listOf<BottomMenuScreen>(
        BottomMenuScreen.Catalog,
        BottomMenuScreen.Favorites
    )

    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        contentColor = MaterialTheme.colors.surface
    ) {
        //Current page from backStack
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        //Current route
        val currentRoute = navBackStackEntry?.destination?.route

        menuItems.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                selectedContentColor = selectedColor,
                unselectedContentColor = unselectedColor,
                onClick = { onBottomIconClick(it.route) },
                label = {
                    Text(text = it.title)
                },
                alwaysShowLabel = true,
                icon = {
                    Icon(imageVector = it.icon, contentDescription = it.title)
                }


            )
        }

    }

}
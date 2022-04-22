package com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.splash_screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.robertconstantindinescu.myoptivamovieapp.R
import com.robertconstantindinescu.myoptivamovieapp.feature_catalog.presentation.util.Constants.SPLASH_SCREEN_DURATION
import com.robertconstantindinescu.myoptivamovieapp.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen(
    onEnterApp: () -> Unit = {}
) {

    val scale = remember {
        //float value holder
        Animatable(0f)
    }
    val overshootInterpolator = remember {
        OvershootInterpolator(2f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 700,
                easing = {
                    //fraction of animation that  is already played.
                    overshootInterpolator.getInterpolation(it)
                }
            )

        )
        delay(SPLASH_SCREEN_DURATION)
        onEnterApp()
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier.scale(scale.value),
            painter = painterResource(id = R.drawable.optiva),
            contentDescription = "Logo"
        )
    }


}




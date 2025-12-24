package com.norah.nooralhadith.ui.theme

import android.app.Activity

import android.os.Build

import androidx.compose.foundation.isSystemInDarkTheme

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.darkColorScheme

import androidx.compose.material3.dynamicDarkColorScheme

import androidx.compose.material3.dynamicLightColorScheme

import androidx.compose.material3.lightColorScheme

import androidx.compose.runtime.Composable

import androidx.compose.runtime.SideEffect

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.platform.LocalView

import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(

    primary = BrandPrimaryDark,          // بدل BrandPrimary

    secondary = BrandSecondaryDark,       // بدل BrandSecondary

    background = DarkBg,

    surface = DarkSurface,

    onPrimary = Color(0xFF1A1419),        // نص فوق primary

    onSecondary = Color(0xFF0E1720),

    onBackground = DarkOn,

    onSurface = DarkOn,

    )


private val LightColorScheme = lightColorScheme(

    primary = BrandPrimary,

    secondary = BrandSecondary,

    background = PastelBlue,

    surface = WhiteSoft,

    onPrimary = Color.White,

    onSecondary = Color.White,

    onBackground = Color(0xFF1F1F1F),

    onSurface = Color(0xFF1F1F1F)

)

@Composable

fun NoorAlHadithTheme(

    darkTheme: Boolean = isSystemInDarkTheme(),

    dynamicColor: Boolean = false,

    content: @Composable () -> Unit

) {

    val colorScheme = when {

        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {

            val context = LocalContext.current

            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)

        }

        darkTheme -> DarkColorScheme

        else -> LightColorScheme

    }

    val view = LocalView.current

    if (!view.isInEditMode) {

        SideEffect {

            val window = (view.context as Activity).window

            // نخلي الستايتس بار مناسب للوضع الفاتح

            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme

        }

    }

    MaterialTheme(

        colorScheme = colorScheme,

        typography = Typography,

        content = content

    )

}

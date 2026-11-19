package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ProLightScheme = lightColorScheme(
    primary = NavyPrimary,
    onPrimary = Color.White,
    primaryContainer = BluePrimarySoft,
    onPrimaryContainer = BlueOnContainer,
    secondary = AmberAccent,
    onSecondary = Color.White,
    secondaryContainer = AmberSoftBg,
    onSecondaryContainer = AmberOnContainer,
    tertiary = EmeraldVerified,
    onTertiary = Color.White,
    tertiaryContainer = EmeraldSoftBg,
    onTertiaryContainer = EmeraldOnContainer,
    background = LightCanvasBg,
    onBackground = LightTextPrimary,
    surface = LightSurface,
    onSurface = LightTextPrimary,
    surfaceVariant = LightSurface,
    onSurfaceVariant = LightTextSecondary,
    surfaceContainerHigh = LightSurfaceElevated,
    outline = LightOutline,
    error = ErrorCoral,
    onError = Color.White
)

private val ProDarkScheme = darkColorScheme(
    primary = SkyPrimaryDark,
    onPrimary = DarkCanvasBg,
    primaryContainer = SkyContainerDark,
    onPrimaryContainer = SkyOnContainerDark,
    secondary = AmberDark,
    onSecondary = DarkCanvasBg,
    secondaryContainer = AmberContainerDark,
    onSecondaryContainer = AmberOnContainerDark,
    tertiary = EmeraldDark,
    onTertiary = DarkCanvasBg,
    tertiaryContainer = EmeraldContainerDark,
    onTertiaryContainer = EmeraldDark,
    background = DarkCanvasBg,
    onBackground = DarkTextPrimary,
    surface = DarkSurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkSurfaceCard,
    onSurfaceVariant = DarkTextSecondary,
    surfaceContainerHigh = DarkSurfaceElevated,
    outline = DarkOutline,
    error = Color(0xFFF87171),
    onError = DarkCanvasBg
)

@Composable
fun CvDavidTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) ProDarkScheme else ProLightScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

package com.mrhakan.chartsymbols.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Ocean = Color(0xFF0E7490)
private val DeepOcean = Color(0xFF0B3B50)
private val Sky = Color(0xFFE3F4F7)
private val Coral = Color(0xFFE76F51)
private val Sand = Color(0xFFFFF7E7)
private val Ink = Color(0xFF173042)

private val LightColors = lightColorScheme(
    primary = Ocean,
    onPrimary = Color.White,
    primaryContainer = Sky,
    onPrimaryContainer = DeepOcean,
    secondary = Coral,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFE1D8),
    onSecondaryContainer = Color(0xFF5B1C0F),
    tertiary = Color(0xFF6B5B95),
    background = Color(0xFFF7F9FC),
    surface = Color.White,
    onSurface = Ink,
    surfaceVariant = Color(0xFFE9EFF4),
    onSurfaceVariant = Color(0xFF536571)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF72D0DC),
    onPrimary = Color(0xFF00363D),
    primaryContainer = Color(0xFF004F58),
    onPrimaryContainer = Color(0xFF9CF0F8),
    secondary = Color(0xFFFFB5A1),
    onSecondary = Color(0xFF571F14),
    background = Color(0xFF0E171D),
    surface = Color(0xFF15232B),
    onSurface = Color(0xFFE2F1F7),
    surfaceVariant = Color(0xFF39484F),
    onSurfaceVariant = Color(0xFFBECBD0)
)

@Composable
fun ChartSymbolsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography(),
        content = content
    )
}

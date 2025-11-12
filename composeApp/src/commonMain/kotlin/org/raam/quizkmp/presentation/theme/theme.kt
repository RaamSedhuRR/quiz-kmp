package org.raam.quizkmp.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

object QuizColors {
    val Primary = Color(0xFF7B1FA2)
    val DarkPurple = Color(0xFF4A148C)
    val Accent = Color(0xFFE040FB)
    val Success = Color(0xFF4CAF50)
    val Error = Color(0xFFF44336)
    val Warning = Color(0xFFFF9800)
    val SurfaceVariant = Color(0xFFE7E0EC)

    val BackgroundGradient = Brush.verticalGradient(
        colors = listOf(DarkPurple, Primary)
    )
}

object QuizSpacing {
    val Small = 8.dp
    val Medium = 16.dp
    val Large = 24.dp
    val ExtraLarge = 40.dp
}

object QuizTypography {
    @Composable
    fun title(color: Color): TextStyle = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.ExtraBold,
        color = color
    )

    @Composable
    fun subtitle(color: Color): TextStyle = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.Bold,
        color = color
    )

    @Composable
    fun body(color: Color): TextStyle = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Medium,
        color = color
    )
}

package org.raam.quizkmp.presentation.quiz.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.raam.quizkmp.presentation.theme.QuizColors
import org.raam.quizkmp.presentation.theme.QuizSpacing
import org.raam.quizkmp.presentation.theme.QuizTypography

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = QuizColors.BackgroundGradient),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = QuizColors.Accent)
            Spacer(modifier = Modifier.height(QuizSpacing.Medium))
            Text("Loading Quiz...", style = QuizTypography.body())
        }
    }
}

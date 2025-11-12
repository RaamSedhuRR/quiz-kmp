package org.raam.quizkmp.presentation.quiz.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.raam.quizkmp.presentation.theme.QuizColors
import org.raam.quizkmp.presentation.theme.QuizSpacing
import org.raam.quizkmp.presentation.theme.QuizTypography

@Composable
fun ResultScreen(
    correct: Int,
    total: Int,
    maxStreak: Int,
    onRestart: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = QuizColors.BackgroundGradient)
            .padding(horizontal = QuizSpacing.Medium, vertical = QuizSpacing.Large)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(QuizSpacing.Large))
            // Nav bar title
            Text(
                text = "Quiz Results",
                style = QuizTypography.subtitle(Color.White),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = QuizSpacing.Medium)
            )

            Text(
                text = "🎉 Quiz Completed!",
                style = QuizTypography.title(Color.White),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(QuizSpacing.Large))

            // Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = QuizSpacing.Medium),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = QuizColors.SurfaceVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = QuizSpacing.Large),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Score circle
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$correct / $total",
                            style = QuizTypography.subtitle(QuizColors.Primary).copy(fontSize = 28.sp)
                        )
                    }

                    Spacer(modifier = Modifier.height(QuizSpacing.Medium))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("🔥", fontSize = 22.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Longest Streak: $maxStreak",
                            style = QuizTypography.body(QuizColors.DarkPurple)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(QuizSpacing.ExtraLarge))

            Button(
                onClick = onRestart,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Restart Quiz",
                    color = QuizColors.DarkPurple,
                    style = QuizTypography.body(Color.White).copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

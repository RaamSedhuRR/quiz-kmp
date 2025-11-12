package org.raam.quizkmp.presentation.quiz.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.raam.quizkmp.presentation.quiz.QuizUiState
import org.raam.quizkmp.presentation.quiz.screens.components.FancyQuizProgressBar
import org.raam.quizkmp.presentation.theme.QuizColors
import org.raam.quizkmp.presentation.theme.QuizSpacing
import org.raam.quizkmp.presentation.theme.QuizTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    state: QuizUiState,
    onOptionSelected: (Int) -> Unit,
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val question = state.questions.getOrNull(state.currentQuestionIndex) ?: return

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Quiz",
                        style = QuizTypography.title(Color.White)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = QuizColors.DarkPurple,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F8F8))
                .padding(innerPadding)
                .padding(horizontal = QuizSpacing.Medium, vertical = QuizSpacing.Small)
        ) {
            FancyQuizProgressBar(state.currentQuestionIndex, state.questions.size)
            Spacer(modifier = Modifier.height(QuizSpacing.Medium))

            // --- Streak display ---
            AnimatedVisibility(visible = state.streak >= 1) {
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFFF3E0))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("🔥", fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Streak: ${state.streak}",
                        fontWeight = FontWeight.Bold,
                        color = QuizColors.Warning
                    )
                }
            }

            Spacer(modifier = Modifier.height(QuizSpacing.Large))

            // --- Question text ---
            Text(
                text = question.question,
                style = QuizTypography.subtitle(Color.Black)
            )

            Spacer(modifier = Modifier.height(QuizSpacing.Medium))

            // --- Options ---
            question.options.forEachIndexed { index, option ->
                val bgColor = when {
                    state.selectedOption != null && index == question.correctOptionIndex -> QuizColors.Success
                    state.selectedOption == index && state.selectedOption != question.correctOptionIndex -> QuizColors.Error
                    else -> QuizColors.SurfaceVariant
                }

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable(enabled = state.selectedOption == null) {
                            onOptionSelected(index)
                            coroutineScope.launch {
                                delay(1500)
                                onNext()
                            }
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = bgColor)
                ) {
                    Text(
                        text = option,
                        modifier = Modifier.padding(16.dp),
                        style = QuizTypography.body(Color.Black)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = onSkip,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.6f),
                shape = RoundedCornerShape(24.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.5.dp)
            ) {
                Text("Skip", color = QuizColors.DarkPurple)
            }

            Spacer(modifier = Modifier.height(QuizSpacing.Small))
        }
    }
}


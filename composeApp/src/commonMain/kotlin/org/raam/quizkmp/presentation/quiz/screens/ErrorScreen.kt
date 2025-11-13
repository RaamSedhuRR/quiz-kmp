package org.raam.quizkmp.presentation.quiz.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.raam.quizkmp.presentation.theme.QuizColors
import org.raam.quizkmp.presentation.theme.QuizTypography
import org.raam.quizkmp.presentation.utils.UiStateHandler
import quizkmp.composeapp.generated.resources.Res
import quizkmp.composeapp.generated.resources.error_image

@Composable
fun ErrorScreen(state: UiStateHandler.Error) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        contentAlignment = Alignment.Center
    ) {
        val scrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(Res.drawable.error_image),
                contentDescription = "Error Image",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Error loading the quiz",
                style = QuizTypography.subtitle(QuizColors.Error)
            )

//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = state.message,
//                style = QuizTypography.body(QuizColors.Error),
//                modifier = Modifier
//                    .padding(horizontal = 24.dp)
//                    .fillMaxWidth(),
//                textAlign = androidx.compose.ui.text.style.TextAlign.Center
//            )
        }
    }
}


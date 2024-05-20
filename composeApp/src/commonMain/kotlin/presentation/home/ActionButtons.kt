package presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import design.EnnyButton
import theme.primary
import theme.white

@Composable
fun ActionButtons(
    onStarted: () -> Unit,
    onExample: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        EnnyButton(
            text = "Get Started",
            onClick = onStarted,
            modifier = Modifier.height(50.dp)
        )
        EnnyButton(
            text = "See example",
            onClick = onExample,
            modifier = Modifier.height(50.dp),
            isFilled = false,
            bgColor = white,
            textColor = primary,
            borderColor = primary
        )
    }
}
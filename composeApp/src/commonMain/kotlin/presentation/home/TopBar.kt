package presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.ennyTextStyle
import theme.greyLight
import theme.primary

@Composable
fun TopBar(
    showButton: Boolean = false,
    onStarter: () -> Unit,
    onExample: () -> Unit
){
    Column {
        Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth().height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Icon(
                imageVector = Icons.Filled.QrCode,
                contentDescription = null,
                tint = primary,
                modifier = Modifier.size(40.dp)
            )

            Text(
                text = "Enny",
                style = ennyTextStyle().copy(
                    color = primary,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                showButton,
                enter = slideInHorizontally(
                    animationSpec = spring(dampingRatio = 2f),
                    initialOffsetX = { -it }
                ),
                exit = slideOutHorizontally(
                    animationSpec = spring(dampingRatio = 2f),
                    targetOffsetX = { 0 }
                )
            ){
                ActionButtons(
                    onStarted = onStarter,
                    onExample = onExample
                )
            }
        }
        Spacer(modifier = Modifier.height(3.dp).fillMaxWidth().background(greyLight))
    }

}
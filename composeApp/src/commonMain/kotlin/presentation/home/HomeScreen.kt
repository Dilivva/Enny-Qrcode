package presentation.home

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import design.Container
import kotlinx.coroutines.delay
import presentation.qrcodedesign.QrCodeScreen
import theme.white


private val animateIn = slideInVertically(
    animationSpec = spring(dampingRatio = 2f),
    initialOffsetY = { height ->  -height  }
)
private val animateOut = slideOutVertically(
    animationSpec = spring(dampingRatio = 2f),
    targetOffsetY = { _ -> 0 }
)

@Composable
fun HomeScreen(){
    var showQrCode by remember { mutableStateOf(false) }
    var showInitial by remember { mutableStateOf(false) }

    LaunchedEffect(Unit){
        delay(500)
        showInitial = true
    }

    Column(modifier = Modifier.fillMaxSize().background(white)) {
        TopBar(
            showButton = showQrCode,
            onStarter = { showQrCode = false; showInitial = true },
            onExample = {}
        )
        Introduction(showInitial = showInitial, onStarted = { showInitial = false; showQrCode = true })
        QrDesign(showQrCode = showQrCode)
    }
}

@Composable
private fun Introduction(
    showInitial: Boolean,
    onStarted: () -> Unit
){
    AnimatedVisibility(
        showInitial,
        enter = animateIn,
        exit = animateOut
    ){
        Container {
            HeaderText()
            DescriptionText()
            ActionButtons(
                onStarted = onStarted,
                onExample = {  }
            )
        }

    }
}


@Composable
private fun QrDesign(
    showQrCode: Boolean
){
    AnimatedVisibility(
        visible = showQrCode,
        enter = fadeIn(spring(2f)),
        exit = fadeOut(tween(400))
    ){
        Container {
            QrCodeScreen()
        }
    }
}





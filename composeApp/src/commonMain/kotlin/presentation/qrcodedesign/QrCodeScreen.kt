package presentation.qrcodedesign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.qrose.options.QrShapes
import theme.greyLight
import theme.primary

@Composable
fun QrCodeScreen(){

    var qrStyle by remember { mutableStateOf(QrShapes()) }
    var color by remember { mutableStateOf(Color.Black) }
    var logoByteArray by remember { mutableStateOf<ByteArray?>(null) }

    Row(
        modifier = Modifier.fillMaxSize().background(primary.copy(alpha = 0.026f), RoundedCornerShape(8.dp)).padding(vertical = 30.dp, horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.Top
    ){
        QrType(modifier = Modifier.weight(1.5f))
        Spacer(modifier = Modifier.weight(0.25f).background(greyLight.copy(alpha = 0.5f)))
        QrPreview(
            modifier = Modifier.weight(4f),
            qrShapes = qrStyle,
            color = color,
            logoByteArray = logoByteArray
        )
        Spacer(modifier = Modifier.weight(0.25f).background(greyLight.copy(alpha = 0.5f)))
        QrStyle(
            modifier = Modifier.weight(3f),
            onStyleSelected = { qrStyle = it },
            onColorSelected = { color = it },
            onLogoSelected = { logoByteArray = it }
        )
    }
}
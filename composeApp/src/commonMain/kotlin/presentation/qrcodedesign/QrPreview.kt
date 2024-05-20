package presentation.qrcodedesign

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import design.EnnyButton
import design.EnnyTextField
import io.github.alexzhirkevich.qrose.ImageFormat
import io.github.alexzhirkevich.qrose.options.*
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import io.github.alexzhirkevich.qrose.toByteArray
import org.jetbrains.compose.resources.ExperimentalResourceApi
import presentation.addBackground
import presentation.downloadImage
import presentation.painterResource
import theme.ennyTextStyle
import theme.white

@OptIn(ExperimentalResourceApi::class)
@Composable
fun QrPreview(
    modifier: Modifier = Modifier,
    qrShapes: QrShapes,
    color: Color,
    logoByteArray: ByteArray?
){
    var qrData by remember { mutableStateOf("https://dilivva.com/download") }
    val logo = if (logoByteArray != null) painterResource(logoByteArray) else null
    val qrcodePainter : Painter = rememberQrCodePainter(
        data = qrData,
        shapes = qrShapes,
        colors = QrColors(
            dark = QrBrush.solid(color),
            ball = QrBrush.solid(color),
            frame = QrBrush.solid(color)
        ),
        logo = QrLogo(
            painter = logo,
            shape = QrLogoShape.circle(),
            size = 0.2f
        )
    )

    Column(
        modifier = modifier.fillMaxHeight().background(white, RoundedCornerShape(15.dp)).padding(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Enter your content",
            style = ennyTextStyle().copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )

        EnnyTextField(
            modifier = Modifier.fillMaxWidth(0.7f),
            text = qrData,
            onTextChange = {
                qrData = it
            }
        )

        Text(
            text = "Live Preview",
            style = ennyTextStyle().copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )

        Image(
            painter = qrcodePainter,
            modifier = Modifier.fillMaxSize(0.5f),
            contentDescription = "QR code referring to the content"
        )

        EnnyButton(
            text = "Download",
            onClick = {
                val byteArray = qrcodePainter.addBackground(1050, 1050).toByteArray(ImageFormat.PNG)
                downloadImage(byteArray, "qr-code.png")
            }
        )
    }
}
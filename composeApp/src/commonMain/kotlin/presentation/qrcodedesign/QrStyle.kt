package presentation.qrcodedesign

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
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
import io.github.alexzhirkevich.qrose.options.*
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import kotlinx.coroutines.launch
import presentation.FilePicker
import presentation.readFileAsByteArray
import theme.*

/**
 * Qr style
 * {FRAME}_{DARKPIXEL}_{EYE}
 * @constructor Create empty Qr style
 */

enum class QrStyle{
    BOX_BOX_BOX,
    BOX_BOX_CIRCLE,
    BOX_BOX_ROUND,
    BOX_CIRCLE_CIRCLE,
    BOX_CIRCLE_ROUND,
    BOX_CIRCLE_BOX,
    BOX_ROUND_ROUND,
    BOX_ROUND_BOX,
    BOX_ROUND_CIRCLE,

    CIRCLE_BOX_BOX,
    CIRCLE_BOX_CIRCLE,
    CIRCLE_BOX_ROUND,
    CIRCLE_CIRCLE_CIRCLE,
    CIRCLE_CIRCLE_ROUND,
    CIRCLE_CIRCLE_BOX,
    CIRCLE_ROUND_ROUND,
    CIRCLE_ROUND_BOX,
    CIRCLE_ROUND_CIRCLE,

    ROUND_BOX_BOX,
    ROUND_BOX_CIRCLE,
    ROUND_BOX_ROUND,
    ROUND_CIRCLE_CIRCLE,
    ROUND_CIRCLE_ROUND,
    ROUND_CIRCLE_BOX,
    ROUND_ROUND_ROUND,
    ROUND_ROUND_BOX,
    ROUND_ROUND_CIRCLE,


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QrStyle(
    modifier: Modifier = Modifier,
    onStyleSelected: (QrShapes) -> Unit,
    onColorSelected: (Color) -> Unit,
    onLogoSelected: (ByteArray) -> Unit
){

    var currentStyle by remember { mutableStateOf(QrStyle.BOX_BOX_BOX) }
    val colors = remember {
        listOf(Color.Black, Color.Blue,Color.Cyan, Color.Green, Color.Magenta, Color.Red, Color.Yellow, Color.LightGray)
    }
    var currentColor by remember { mutableStateOf(Color.Black) }
    var showLogoPicker by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    FilePicker(
        show = showLogoPicker,
        initialDirectory = null,
        fileExtensions = listOf("png","jpg","jpeg"),
        title = "Pick a logo",
        onFileSelected = {
            coroutineScope.launch {
                val bytes = it.readFileAsByteArray()
                onLogoSelected(bytes)
            }
        }
    )

    LazyVerticalGrid(
        modifier = modifier.fillMaxHeight().background(white, RoundedCornerShape(8.dp)),
        columns = GridCells.Fixed(5),
        state = rememberLazyGridState(),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ){
        item(span = { GridItemSpan(5) }) {
            Text(
                text = "Pattern",
                style = ennyTextStyle().copy(
                    color = black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
            )
        }
        items(QrStyle.values()){ style ->
            StyleItem(
                qrStyle = style,
                isSelected = currentStyle == style,
                onClick = { currentStyle = style; onStyleSelected(it) }
            )
        }

        item(span = { GridItemSpan(5) }) {
            Text(
                text = "Colors",
                style = ennyTextStyle().copy(
                    color = black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        items(colors){
            ColorItem(
                color = it,
                isSelected = currentColor == it,
                onClick = { currentColor = it; onColorSelected(it) }
            )
        }
        item(span = { GridItemSpan(5) }) {
            Text(
                text = "Add logo",
                style = ennyTextStyle().copy(
                    color = black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        item(span = { GridItemSpan(5) }) {
            EnnyButton(
                onClick = { showLogoPicker = true },
                text = "Pick a logo"
            )
        }
    }
}

@Composable
private fun StyleItem(
    qrStyle: QrStyle,
    isSelected: Boolean,
    onClick: (QrShapes) -> Unit
){

    val shape = remember(qrStyle){
        QrShapes(
            code = QrCodeShape.Default,
            darkPixel = getDarkPixelShape(qrStyle),
            ball = getEyeShape(qrStyle),
            frame = getFrameShape(qrStyle)
        )
    }
    val qrcodePainter : Painter = rememberQrCodePainter(
        data = "qr",
        shapes = shape
    )

    Column(
        modifier = Modifier.size(50.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = { onClick(shape) },
            modifier = Modifier.size(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = white,
                contentColor = black
            ),
            border = BorderStroke(1.dp, if (isSelected) primary else greyLight),
            shape = RoundedCornerShape(3.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 1.dp
            )
        ) {
            Icon(
                painter = qrcodePainter,
                contentDescription = "qr style",
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
private fun ColorItem(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.size(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = { onClick() },
            modifier = Modifier.size(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = white,
                contentColor = black
            ),
            border = BorderStroke(1.dp, if (isSelected) primary else greyLight),
            shape = RoundedCornerShape(3.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 1.dp
            )
        ) {
            Spacer(modifier = Modifier.size(15.dp).background(color))
        }
    }
}



private fun getFrameShape(qrStyle: QrStyle): QrFrameShape{
    return when(qrStyle){
        QrStyle.BOX_BOX_BOX,
        QrStyle.BOX_BOX_CIRCLE,
        QrStyle.BOX_BOX_ROUND,
        QrStyle.BOX_CIRCLE_CIRCLE,
        QrStyle.BOX_CIRCLE_ROUND,
        QrStyle.BOX_CIRCLE_BOX,
        QrStyle.BOX_ROUND_ROUND,
        QrStyle.BOX_ROUND_BOX,
        QrStyle.BOX_ROUND_CIRCLE -> QrFrameShape.square()

        QrStyle.CIRCLE_BOX_BOX,
        QrStyle.CIRCLE_BOX_CIRCLE,
        QrStyle.CIRCLE_BOX_ROUND,
        QrStyle.CIRCLE_CIRCLE_CIRCLE,
        QrStyle.CIRCLE_CIRCLE_ROUND,
        QrStyle.CIRCLE_CIRCLE_BOX,
        QrStyle.CIRCLE_ROUND_ROUND,
        QrStyle.CIRCLE_ROUND_BOX,
        QrStyle.CIRCLE_ROUND_CIRCLE -> QrFrameShape.asPixel(QrPixelShape.circle())

        QrStyle.ROUND_BOX_BOX,
        QrStyle.ROUND_BOX_CIRCLE,
        QrStyle.ROUND_BOX_ROUND,
        QrStyle.ROUND_CIRCLE_CIRCLE,
        QrStyle.ROUND_CIRCLE_ROUND,
        QrStyle.ROUND_CIRCLE_BOX,
        QrStyle.ROUND_ROUND_ROUND,
        QrStyle.ROUND_ROUND_BOX,
        QrStyle.ROUND_ROUND_CIRCLE -> QrFrameShape.roundCorners(.25f)
    }
}

private fun getEyeShape(qrStyle: QrStyle): QrBallShape{
    return when(qrStyle){
        QrStyle.BOX_BOX_BOX,
        QrStyle.BOX_CIRCLE_BOX,
        QrStyle.BOX_ROUND_BOX,
        QrStyle.CIRCLE_BOX_BOX,
        QrStyle.CIRCLE_CIRCLE_BOX,
        QrStyle.CIRCLE_ROUND_BOX,
        QrStyle.ROUND_BOX_BOX,
        QrStyle.ROUND_CIRCLE_BOX,
        QrStyle.ROUND_ROUND_BOX -> QrBallShape.square()
        QrStyle.BOX_BOX_CIRCLE,
        QrStyle.BOX_CIRCLE_CIRCLE,
        QrStyle.BOX_ROUND_CIRCLE,
        QrStyle.CIRCLE_BOX_CIRCLE,
        QrStyle.CIRCLE_CIRCLE_CIRCLE,
        QrStyle.CIRCLE_ROUND_CIRCLE,
        QrStyle.ROUND_BOX_CIRCLE,
        QrStyle.ROUND_CIRCLE_CIRCLE,
        QrStyle.ROUND_ROUND_CIRCLE-> QrBallShape.asPixel(QrPixelShape.circle())
        QrStyle.BOX_BOX_ROUND,
        QrStyle.BOX_CIRCLE_ROUND,
        QrStyle.BOX_ROUND_ROUND,
        QrStyle.CIRCLE_BOX_ROUND,
        QrStyle.CIRCLE_CIRCLE_ROUND,
        QrStyle.CIRCLE_ROUND_ROUND,
        QrStyle.ROUND_BOX_ROUND,
        QrStyle.ROUND_CIRCLE_ROUND,
        QrStyle.ROUND_ROUND_ROUND -> QrBallShape.roundCorners(.25f)
    }
}

private fun getDarkPixelShape(qrStyle: QrStyle): QrPixelShape{
    return when(qrStyle){
        QrStyle.BOX_BOX_BOX,
        QrStyle.BOX_BOX_CIRCLE,
        QrStyle.BOX_BOX_ROUND,
        QrStyle.CIRCLE_BOX_BOX,
        QrStyle.CIRCLE_BOX_CIRCLE,
        QrStyle.CIRCLE_BOX_ROUND,
        QrStyle.ROUND_BOX_BOX,
        QrStyle.ROUND_BOX_CIRCLE,
        QrStyle.ROUND_BOX_ROUND -> QrPixelShape.square()
        QrStyle.BOX_CIRCLE_CIRCLE,
        QrStyle.BOX_CIRCLE_ROUND,
        QrStyle.BOX_CIRCLE_BOX,
        QrStyle.CIRCLE_CIRCLE_CIRCLE,
        QrStyle.CIRCLE_CIRCLE_ROUND,
        QrStyle.CIRCLE_CIRCLE_BOX,
        QrStyle.ROUND_CIRCLE_CIRCLE,
        QrStyle.ROUND_CIRCLE_ROUND,
        QrStyle.ROUND_CIRCLE_BOX -> QrPixelShape.circle()
        QrStyle.BOX_ROUND_ROUND,
        QrStyle.BOX_ROUND_BOX,
        QrStyle.BOX_ROUND_CIRCLE,
        QrStyle.CIRCLE_ROUND_ROUND,
        QrStyle.CIRCLE_ROUND_BOX,
        QrStyle.CIRCLE_ROUND_CIRCLE,
        QrStyle.ROUND_ROUND_ROUND,
        QrStyle.ROUND_ROUND_BOX,
        QrStyle.ROUND_ROUND_CIRCLE -> QrPixelShape.roundCorners(.25f)
    }
}
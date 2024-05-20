package presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image


expect class PlatformFile

typealias FileSelected = (PlatformFile) -> Unit


@Composable
expect fun FilePicker(
    show: Boolean,
    initialDirectory: String?,
    fileExtensions: List<String>,
    title: String?,
    onFileSelected: FileSelected
)

expect suspend fun PlatformFile.readFileAsByteArray(): ByteArray

@Composable
fun painterResource(byteArray: ByteArray): Painter{
    return remember(byteArray) {
        val imageBitmap = Image.makeFromEncoded(byteArray).toComposeImageBitmap()
        BitmapPainter(imageBitmap)
    }
}

expect fun downloadImage(byteArray: ByteArray, filename: String)

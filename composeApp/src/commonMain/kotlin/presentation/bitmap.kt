package presentation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import theme.white


fun Painter.addBackground(
    width : Int,
    height : Int,
    alpha : Float = 1f,
): ImageBitmap {

    val bmp = ImageBitmap(width, height)
    val canvas = Canvas(bmp)

    CanvasDrawScope().draw(
        density = Density(1f, 1f),
        layoutDirection = LayoutDirection.Ltr,
        canvas = canvas,
        size = Size(width.toFloat(), height.toFloat())
    ) {
        drawRect(
            color = white,
            topLeft = Offset.Zero,
            size = Size(this.size.width + 10, this.size.height + 10),
        )
        draw(this@draw.size, alpha)
    }

    return bmp
}
package theme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val LightColor = lightColors(
    primary = primary,
    primaryVariant = tertiary,
    secondary = secondary,
    secondaryVariant = secondaryLight,
    background = greyLight,
    surface = greyLight,
    error = red,
    onPrimary = white,
    onSecondary = white,
    onBackground = grey,
    onSurface = white,
    onError = white
)

val HubShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(8.dp)
)

@Composable
fun CodexTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColor,
        shapes = HubShapes,
        typography = HubTypography()
    ){
        Surface {
            content()
        }
    }
}
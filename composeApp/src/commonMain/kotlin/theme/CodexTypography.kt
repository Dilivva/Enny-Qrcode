@file:OptIn(ExperimentalResourceApi::class)
package theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import codex.composeapp.generated.resources.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Jarkata(): FontFamily{
    return FontFamily(
        Font(Res.font.jakarta_regular, weight = FontWeight.Normal),
        Font(Res.font.jakarta_semibold, weight = FontWeight.SemiBold),
        Font(Res.font.jakarta_bold, weight = FontWeight.Bold),
        Font(Res.font.jakarta_medium, weight = FontWeight.Medium)
    )
}

val HubTypography = @Composable {
    Typography(
        defaultFontFamily = Jarkata()
    )
}
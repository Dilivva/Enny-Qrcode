package design

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import theme.charcoal
import theme.greyLight
import theme.primary
import theme.white

@Composable
fun EnnyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isFilled: Boolean = false,
    bgColor: Color = primary,
    textColor: Color = white,
    borderColor: Color = primary
){
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = bgColor,
            contentColor = textColor,
            disabledBackgroundColor = greyLight,
            disabledContentColor = charcoal
        ),
        border = if (isFilled) null else BorderStroke(1.dp, borderColor),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ){
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}
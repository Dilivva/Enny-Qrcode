package presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.black
import theme.grey
import theme.primary

@Composable
fun DescriptionText(){
    Column(modifier = Modifier.wrapContentWidth()){
        Text(
            text = "Enny is a dynamic QR code generator. It allows users to easily create customized QR codes for sharing or embedding on their website.",
            fontSize = 16.sp,
            color = grey,
            modifier = Modifier.width(400.dp),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun HeaderText(){
    val text = buildAnnotatedString {
        append(
            AnnotatedString(
                text = "Dynamic ",
                spanStyle = SpanStyle(color = primary, fontSize = 46.sp, fontWeight = FontWeight.ExtraBold)
            )
        )
        append(
            AnnotatedString(
                text = "QR Codes.",
                spanStyle = SpanStyle(color = black, fontSize = 46.sp, fontWeight = FontWeight.ExtraBold),
            )
        )
    }
    Column(
        modifier = Modifier.padding(20.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Generate and Publish",
            color = black,
            fontSize = 46.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = text
        )
    }

}
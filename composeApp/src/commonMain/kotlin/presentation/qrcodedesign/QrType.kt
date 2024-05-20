package presentation.qrcodedesign

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.*


enum class QrType{
    LINK, TEXT, EMAIL, WIFI, PHONE, SMS, LOCATION
}

@Composable
fun QrType(
    modifier: Modifier = Modifier
){
    var currentType by remember { mutableStateOf(QrType.LINK) }

    Column(
        modifier = modifier.fillMaxHeight().background(white, RoundedCornerShape(8.dp)).padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Destination",
            style = ennyTextStyle().copy(
                color = black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ){
            items(QrType.values()){
                TypeItem(
                    qrType = it,
                    isSelected = currentType == it,
                    onClick = { currentType = it }
                )
            }
        }
    }
}

@Composable
private fun TypeItem(
    qrType: QrType,
    isSelected: Boolean,
    onClick: () -> Unit
){
    val (icon, text) = getIconAndName(qrType)
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = onClick,
            modifier = Modifier.size(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isSelected) white else greyLight,
                contentColor = if (isSelected) primary else grey
            ),
            border = if (isSelected) BorderStroke(1.dp, primary) else null,
            shape = RoundedCornerShape(3.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            )
        ){
            Icon(
                imageVector = icon,
                contentDescription = "QrCodeType",
            )
        }
        Text(
            text = text,
            style = ennyTextStyle().copy(
                color = black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

private fun getIconAndName(qrType: QrType): Pair<ImageVector, String>{
    return when(qrType){
        QrType.LINK -> Icons.Filled.Link to "Website"
        QrType.TEXT -> Icons.AutoMirrored.Filled.Article to "Text"
        QrType.EMAIL -> Icons.Filled.Email to "Email"
        QrType.WIFI -> Icons.Filled.Wifi to "WiFi"
        QrType.PHONE -> Icons.Filled.Phone to "Phone no"
        QrType.SMS -> Icons.Filled.Sms to "SMS"
        QrType.LOCATION -> Icons.Filled.Place to "Location"
    }
}
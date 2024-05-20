package design

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Container(
    enableScroll: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
){
    val modifier = if (enableScroll) {
        Modifier.fillMaxSize().padding(20.dp).verticalScroll(rememberScrollState(), enabled = true)
    }else{
        Modifier.fillMaxSize().padding(20.dp)
    }
    Box(
        modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}
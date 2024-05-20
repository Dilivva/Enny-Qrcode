package design

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.*


@Composable
private fun textFieldColor(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        backgroundColor = white,
        focusedIndicatorColor = white,
        unfocusedIndicatorColor = white,
        disabledIndicatorColor = white,
        errorIndicatorColor = white,
        placeholderColor = greyLight,
        leadingIconColor = black
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DefaultDecorator(
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    value: String,
    placeHolder: String,
    singleLine: Boolean,
    innerTextField: @Composable () -> Unit
){
    TextFieldDefaults.TextFieldDecorationBox(
        value = value,
        visualTransformation = VisualTransformation.None,
        innerTextField = innerTextField,
        enabled = true,
        placeholder = {
            Text(text = placeHolder, style = MaterialTheme.typography.body1.copy(color = Color.Gray))
        },
        singleLine = singleLine,
        isError = false,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        interactionSource = remember { MutableInteractionSource() },
        colors = textFieldColor(),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 15.dp)
    )
}

@Composable
fun EnnyTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    label: String? = null,
    bg: Color = grey.copy(0.1f),
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    capitalizeType: KeyboardCapitalization = KeyboardCapitalization.None,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    decorator: @Composable ( @Composable () -> Unit ) -> Unit = {
        DefaultDecorator(
            value = text,
            placeHolder = placeholder,
            singleLine = true,
            innerTextField = it,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon
        )
    }
) {
    Column(
        modifier = Modifier
    ) {
        if (label != null) {
            Text(
                text = label,
                color = Color.Gray,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        BasicTextField(
            value = text,
            enabled = enabled,
            singleLine = true,
            onValueChange = onTextChange,
            textStyle = ennyTextStyle().copy(
                fontSize = 16.sp
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                capitalization = capitalizeType
            ),
            modifier = modifier
                .background(bg, RoundedCornerShape(8.dp))
                .border(BorderStroke(1.dp, primary), RoundedCornerShape(8.dp))
            ,
            decorationBox = @Composable { innerTextField ->
                decorator(innerTextField)
            },
            visualTransformation = visualTransformation
        )

    }
}





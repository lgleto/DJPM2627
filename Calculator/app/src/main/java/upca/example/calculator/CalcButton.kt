package upca.example.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import upca.example.calculator.ui.theme.CalculatorTheme


@Composable
fun CalcButton(
    modifier: Modifier = Modifier,
    label : String = "",
    onButtonPressed : (String) -> Unit
){
    Button(
        onClick = {
            onButtonPressed(label)
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red
        )
    ) {
        Text(
            text = label,
            modifier = Modifier,
            fontSize = TextUnit(
                value = 48.0f,
                type = TextUnitType.Sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalcButtonPreview(){
    CalculatorTheme {
        CalcButton(
            label = "2"
        ){}
    }
}

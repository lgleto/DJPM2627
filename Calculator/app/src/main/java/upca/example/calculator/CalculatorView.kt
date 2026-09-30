package upca.example.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import upca.example.calculator.ui.theme.CalculatorTheme

@Composable
fun CalculatorView(
    modifier: Modifier = Modifier
){

    var displayText by remember { mutableStateOf("0") }
    var userIsInTheMiddleOfIntroduction by remember { mutableStateOf(false) }
    var operation by remember { mutableStateOf<String?>(null) }
    var operand  by remember { mutableStateOf(0.0) }

    val onNumPressed : (String)->(Unit) = { num ->
        if (userIsInTheMiddleOfIntroduction) {
            if (num == ".") {
                if (!displayText.contains(".")) {
                    displayText += num
                }
            } else {
                if (displayText == "0") {
                    displayText = num
                } else {
                    displayText += num
                }
            }
        }else{
            displayText = num
        }

        userIsInTheMiddleOfIntroduction  = true
    }

    val onOperationPressed : (String)->(Unit) = { op ->

        var result = displayText.toDouble()
        when (operation) {
            "+" -> result = displayText.toDouble() + operand
            "-" -> result = displayText.toDouble() - operand
            "×" -> result = displayText.toDouble() * operand
            "÷" -> result = displayText.toDouble() / operand
        }

        if (result % 1.0 == 0.0 ){
            displayText = "${result.toInt()}"
        }else{
            displayText = "$result"
        }
        operation = op
        operand = displayText.toDouble()
        userIsInTheMiddleOfIntroduction  = false
    }


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = displayText,
            modifier = Modifier.fillMaxWidth().weight(1f),
            textAlign = TextAlign.Right,
            fontSize = TextUnit( value = 58.0f, type = TextUnitType.Sp)
        )
        Row(modifier = Modifier.weight(1f))  {
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "C",
                color = MaterialTheme.colorScheme.tertiary,
                onButtonPressed = {

                }
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "E",

                color = MaterialTheme.colorScheme.tertiary,
                onButtonPressed = {}
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "%",
                color = MaterialTheme.colorScheme.tertiary,
                onButtonPressed = onOperationPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "√",
                color = MaterialTheme.colorScheme.tertiary,
                onButtonPressed = onOperationPressed
            )
        }
        Row(modifier = Modifier.weight(1f))  {
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "9",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "8",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "7",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "+",
                color = MaterialTheme.colorScheme.tertiary,
                onButtonPressed = onOperationPressed
            )
        }
        Row(modifier = Modifier.weight(1f))  {
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "6",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "5",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "4",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "-",
                color = MaterialTheme.colorScheme.tertiary,
                onButtonPressed = onOperationPressed
            )
        }
        Row(modifier = Modifier.weight(1f))  {
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "1",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "2",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "3",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "×",
                color = MaterialTheme.colorScheme.tertiary,
                onButtonPressed = onOperationPressed
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "0",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = ".",
                onButtonPressed = onNumPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "=",
                color = MaterialTheme.colorScheme.tertiary,
                onButtonPressed = onOperationPressed
            )
            CalcButton(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f),
                label = "÷",
                color = MaterialTheme.colorScheme.tertiary,
                onButtonPressed = onOperationPressed
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CalculatorViewPreview(){
    CalculatorTheme {
        CalculatorView()
    }
}

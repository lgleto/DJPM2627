package upca.example.helloworld

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainView(
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf( "Hello World!") }
    Column(
        modifier = modifier
    ) {
        Text(text)
        Button(
            onClick = {
                text = "Olá Mundo!"
            }
        ){
            Text("Traduzir")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview(){
    MainView()
}
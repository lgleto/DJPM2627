package upca.example.myshoppinglist.ui.login

import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.myshoppinglist.Screen
import upca.example.myshoppinglist.ui.theme.MyShoppingListTheme

@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
){

    val viewModel : LoginViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = uiState.email,
            label = { Text("Email") },
            modifier = Modifier.padding(8.dp),
            onValueChange = {
                viewModel.updateEmail(it)
            }
        )
        TextField(
            value = uiState.password,
            label = { Text("Password") },
            modifier = Modifier.padding(8.dp),
            onValueChange = {
                viewModel.updatePassword(it)
            }
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                viewModel.login(){
                    navController.navigate(Screen.Home.route)
                }
            },
            enabled = !uiState.isLoading
        ) {
            Text("Login")
            if (uiState.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.padding(8.dp),
                    color = Color.White,
                )
            }
        }
        uiState.error?.let{
            Text(it)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    MyShoppingListTheme() {
        LoginView()
    }
}
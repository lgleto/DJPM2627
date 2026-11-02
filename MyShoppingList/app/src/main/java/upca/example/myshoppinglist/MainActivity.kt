package upca.example.myshoppinglist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import upca.example.myshoppinglist.ui.components.MyTopBar
import upca.example.myshoppinglist.ui.login.LoginView
import upca.example.myshoppinglist.ui.theme.MyShoppingListTheme

const val TAG = "MyShoppingList"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var title by remember { mutableStateOf("My Shopping List") }
            var isBaseScreen by remember { mutableStateOf(true) }
            var topBarActions by remember {mutableStateOf<@Composable () -> Unit>({})}

            LaunchedEffect(Unit) {
                val currentUser = Firebase.auth.currentUser
                if (currentUser != null) {
                    navController.navigate(Screen.Home.route)
                } else {
                    navController.navigate(Screen.Login.route)
                }
            }


            MyShoppingListTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MyTopBar(
                            navController = navController,
                            title = title,
                            isBaseScreen = isBaseScreen,
                            actions = topBarActions
                        )
                    },
                ) { innerPadding ->
                    NavHost(navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)) {
                        composable(route = Screen.Home.route) {
                            isBaseScreen = true
                            title = "Home"

                            topBarActions = {
                                IconButton(
                                    onClick = {
                                        Firebase.auth.signOut()
                                        navController.navigate(Screen.Login.route)
                                    }
                                ) {
                                    Text("Logout")
                                }
                            }
                            Text(text = "Home")
                        }
                        composable(route = Screen.Login.route) {
                            isBaseScreen = true
                            title = "Login"
                            topBarActions = {}
                            LoginView(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
}
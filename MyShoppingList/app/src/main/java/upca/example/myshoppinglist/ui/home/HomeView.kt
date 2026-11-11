package upca.example.myshoppinglist.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import upca.example.myshoppinglist.R
import upca.example.myshoppinglist.Screen
import upca.example.myshoppinglist.ui.theme.MyShoppingListTheme

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
){

    val viewModel : HomeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchKarts()
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        if (uiState.isLoading) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading karts...   ")
            }
        } else if (uiState.karts.isEmpty()) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No karts found!")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(
                    items = uiState.karts,
                ) { index, kart ->
                    Text(
                        kart.name ?: "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable{
                                navController.navigate(
                                    Screen.Kart.route
                                        .replace("{kartId}", kart.id!!)
                                )
                            }
                    )
                }
            }
        }

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                navController.navigate(Screen.AddKart.route)
            }) {
            Text("Add kart")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    MyShoppingListTheme() {
        HomeView()
    }
}
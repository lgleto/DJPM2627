package upca.example.topnews.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.topnews.R
import upca.example.topnews.Screen
import upca.example.topnews.ui.theme.TopNewsTheme


data class BottomBarItem(
    val route : String,
    val icon : Int,
    val label : String
)

@Composable
fun MyBottomBar(
    navController: NavController = rememberNavController()
) {

    var selectedIndex by remember { mutableStateOf(0) }

    val items = listOf(
        BottomBarItem(
            route = Screen.Home.route,
            icon = R.drawable.house_solid_full,
            label = "Home"
        ),
        BottomBarItem(
            route = Screen.Sports.route,
            icon = R.drawable.person_running_solid_full,
            label = "Sports"
        ),
        BottomBarItem(
            route = Screen.Health.route,
            icon = R.drawable.heart_solid_full,
            label = "Health"
        ),
        BottomBarItem(
            route = Screen.Technology.route,
            icon = R.drawable.microchip_solid_full,
            label = "Technology"
        )
    )

    BottomAppBar() {
        NavigationBar() {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = {
                        navController.navigate(item.route)
                        selectedIndex = index
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = item.label
                        )
                    },
                    label = {
                        Text(item.label)
                    }
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyBottomBarPreview() {
    TopNewsTheme {
        MyBottomBar()
    }
}
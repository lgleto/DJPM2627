package upca.example.topnews

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import upca.example.topnews.ui.components.MyBottomBar
import upca.example.topnews.ui.components.MyTopBar
import upca.example.topnews.ui.home.ArticleDetailView
import upca.example.topnews.ui.home.ArticlesView
import upca.example.topnews.ui.theme.TopNewsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var title by remember { mutableStateOf("Top News") }
            var isBaseScreen by remember { mutableStateOf(true) }
            var topBarActions by remember {mutableStateOf<@Composable () -> Unit>({})}
            TopNewsTheme {
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
                    bottomBar = {
                        MyBottomBar(
                            navController = navController
                        )
                    }
                ) { innerPadding ->
                    NavHost(navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)) {
                        composable( route = Screen.Home.route){
                            isBaseScreen = true
                            title = "Top News"
                            topBarActions = {}
                            ArticlesView(
                                navController = navController,
                                category = ""
                            )
                        }
                        composable( route = Screen.Sports.route){
                            isBaseScreen = true
                            title = "Sports"
                            topBarActions = {}
                            ArticlesView(
                                navController = navController,
                                category = "sports"
                            )
                        }
                        composable( route = Screen.Health.route){
                            isBaseScreen = true
                            title = "Health"
                            topBarActions = {}
                            ArticlesView(
                                navController = navController,
                                category = "health"
                            )
                        }
                        composable( route = Screen.Technology.route){
                            isBaseScreen = true
                            title = "Technology"
                            topBarActions = {}
                            ArticlesView(
                                navController = navController,
                                category = "technology"
                            )
                        }
                        composable( route = Screen.ArticleDetail.route){ backStackEntry ->
                            isBaseScreen = false
                            title = backStackEntry.arguments?.getString("title")?:""
                            val url = (backStackEntry.arguments?.getString("url")?:"").decodeURL()
                            topBarActions = {
                                IconButton(
                                    onClick = {
                                        val intentShare = Intent(Intent.ACTION_SEND)
                                        intentShare.setType("text/plain")
                                        intentShare.putExtra(Intent.EXTRA_SUBJECT,title)
                                        intentShare.putExtra(Intent.EXTRA_TEXT,url)
                                        startActivity(Intent.createChooser(intentShare,"Partilhar"))
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_share_24),
                                        contentDescription = "share"
                                    )
                                }
                            }
                            ArticleDetailView(
                                url = url
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
    object Sports : Screen("sports")
    object Health : Screen("health")
    object Technology : Screen("technology")
    object ArticleDetail : Screen("article_detail/{title}/{url}")
}
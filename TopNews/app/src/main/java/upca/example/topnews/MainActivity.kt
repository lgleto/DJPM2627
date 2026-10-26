package upca.example.topnews

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
                    }
                ) { innerPadding ->
                    NavHost(navController = navController,
                        startDestination = Screen.Articles.route,
                        modifier = Modifier.padding(innerPadding)) {
                        composable( route = Screen.Articles.route){
                            isBaseScreen = true
                            title = "Top News"
                            topBarActions = {}
                            ArticlesView(
                                navController = navController
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
    object Articles : Screen("articles")
    object ArticleDetail : Screen("article_detail/{title}/{url}")
}
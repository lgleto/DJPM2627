package upca.example.topnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

            TopNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController,
                        startDestination = "articles",
                        modifier = Modifier.padding(innerPadding)) {
                        composable( route = "articles"){
                            ArticlesView(
                                navController = navController
                            )
                        }
                        composable( route = "article_detail/{title}/{url}"){ backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title")?:""
                            val url = (backStackEntry.arguments?.getString("url")?:"").decodeURL()
                            ArticleDetailView(
                                title = title,
                                url = url
                            )
                        }
                    }
                }
            }
        }
    }
}

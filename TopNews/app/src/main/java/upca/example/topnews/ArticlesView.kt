package upca.example.topnews

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.google.apps.card.v1.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import upca.example.topnews.Article
import upca.example.topnews.ui.theme.TopNewsTheme
import java.io.IOException
import java.util.Date
import kotlin.collections.arrayListOf

@Composable
fun ArticlesView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
){

    val viewModel : ArticlesViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    ArticlesViewContent(
        modifier = modifier,
        uiState = uiState,
        navController = navController
    )

    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }

}

@Composable
fun ArticlesViewContent(
    modifier: Modifier = Modifier,
    uiState: ArticlesViewState = ArticlesViewState(),
    navController: NavController = rememberNavController()
){

    when {
        uiState.error != null ->{
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = uiState.error,
                    fontSize = TextUnit(16f, TextUnitType.Sp)
                )
            }
        }
        uiState.isLoading ->{
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        else -> {
            LazyColumn(
                modifier = modifier
            ) {
                itemsIndexed(
                    items = uiState.articles
                ) { index, article ->
                    ArticleViewRow(
                        modifier = Modifier
                            .clickable {
                                //https://news.google.com/rss/articles/CBMiW0FVX3lxTFB6MTJzVzlOQ3h3T3UweTdNRjRrUlRhcUhhVWlGZ2EwWG84SnRxRkNFQVBaSldicVlKYzY5b25LN0hBOE9LTi1UcU51Z2xwWVVwX2kxTXZnWm1xVEU?oc=5"
                                navController
                                    .navigate(
                                        "article_detail/{title}/{url}"
                                            .replace("{title}", article.title?:"")
                                            .replace("{url}", (article.url?:"").encodeURL())
                                    )
                            },
                        article = article)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlesViewPreview() {
    TopNewsTheme() {
        ArticlesViewContent(
            uiState =
                ArticlesViewState(
                    articles = arrayListOf<Article>(
                        Article(
                            title = "Inicio de Ano na UPCA!",
                            description = "..."
                        ),
                        Article(
                            title = "O Braga foi roubado contra o Sporting!",
                            description = "..."
                        ),
                        Article(
                            title = "O Ronaldo quer volta à seleção!",
                            description = "..."
                        ),
                        Article(
                            title = "Inicio de Ano na UPCA!",
                            description = "..."
                        ),
                        Article(
                            title = "O Braga foi roubado contra o Sporting!",
                            description = "..."
                        ),
                        Article(
                            title = "O Ronaldo quer volta à seleção!",
                            description = "..."
                        ),
                    ),
                    isLoading = false,
                    error = null
                )
        )
    }
}
package upca.example.topnews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
    modifier: Modifier = Modifier
){

    val viewModel : ArticlesViewModel = viewModel()
    val articles by viewModel.articles.collectAsState()

    ArticlesViewContent(
        modifier = modifier,
        articles = articles
    )

    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }

}

@Composable
fun ArticlesViewContent(
    modifier: Modifier = Modifier,
    articles: List<Article> = arrayListOf()
){

    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(
            items = articles
        ){ index, article ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(article.title ?: "",
                    fontSize = TextUnit(24f, TextUnitType.Sp)

                )
                Text(article.description ?: "")
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "image article"
                )
                Text((article.publishedAt?: java.util.Date()).toString() )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun ArticlesViewPreview(){
    TopNewsTheme() {
        ArticlesViewContent(
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
        )

        )
    }
}
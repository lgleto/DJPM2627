package upca.example.topnews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import upca.example.topnews.ui.theme.TopNewsTheme

@Composable
fun ArticleViewRow(
    modifier: Modifier = Modifier,
    article : Article = Article()
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(article.title ?: "",
            fontSize = TextUnit(24f, TextUnitType.Sp)

        )
        Text(article.description ?: "")
        article.urlToImage?.let { url ->
            AsyncImage(
                model = url,
                contentDescription = "image article"
            )
        }?:run {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "image article"
            )
        }
        Text((article.publishedAt?: java.util.Date()).toShortDateTime() )
    }

}

@Preview(showBackground = true)
@Composable
fun ArticleViewRowPreview() {
    TopNewsTheme() {
        ArticleViewRow(
            modifier = Modifier,
            article = Article(
                "Teste de noticia",
                description = "teste de descrição"
            )
        )
    }
}
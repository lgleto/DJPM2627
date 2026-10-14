package upca.example.topnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.util.Date

class ArticlesViewModel : ViewModel() {

    private val _articles = MutableStateFlow(arrayListOf<Article>())
    val articles : StateFlow<List<Article>> = _articles.asStateFlow()

    fun fetchArticles(){
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=1765f87e4ebc40229e80fd0f75b6416c")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                val result = response.body!!.string()
                val jsonResult = JSONObject(result)
                val status = jsonResult.getString("status")
                if (status == "ok") {
                    val articlesJson = jsonResult.getJSONArray("articles")
                    val articlesResult = arrayListOf<Article>()
                    for (index in  0..<articlesJson.length()){
                        val articleJson = articlesJson.getJSONObject(index)
                        val title = articleJson.getString("title")
                        val description = articleJson.getString("description")
                        val url = articleJson.getString("url")
                        val urlToImage = articleJson.getString("urlToImage")
                        val publishedAt = articleJson.getString("publishedAt")
                        val article = Article(
                            title = title,
                            description = description,
                            url = url,
                            urlToImage = urlToImage,
                            publishedAt = Date()
                        )
                        articlesResult.add(article)
                    }
                    viewModelScope.launch(Dispatchers.Main) {
                        _articles.value = articlesResult
                    }
                }
            }
        }
    }
}
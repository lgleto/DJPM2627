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

data class ArticlesViewState (
    val articles : ArrayList<Article> = arrayListOf(),
    val error : String? = null,
    var isLoading : Boolean = false,
)

class ArticlesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesViewState())
    val uiState : StateFlow<ArticlesViewState> = _uiState.asStateFlow()

    fun fetchArticles(){
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=1765f87e4ebc40229e80fd0f75b6416c")
                .build()

            try {
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        viewModelScope.launch(Dispatchers.Main) {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = "error ${response.code}"
                            )
                        }
                    }
                    val result = response.body!!.string()
                    val jsonResult = JSONObject(result)
                    val status = jsonResult.getString("status")
                    if (status == "ok") {
                        val articlesJson = jsonResult.getJSONArray("articles")
                        val articlesResult = arrayListOf<Article>()
                        for (index in 0..<articlesJson.length()) {
                            val articleJson = articlesJson.getJSONObject(index)
                            articlesResult.add(Article.fromJson(articleJson))
                        }
                        viewModelScope.launch(Dispatchers.Main) {
                            _uiState.value = _uiState.value.copy(
                                articles = articlesResult,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                }
            }catch (e: Exception){
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "error: ${e.localizedMessage}"
                )
            }
        }
    }
}
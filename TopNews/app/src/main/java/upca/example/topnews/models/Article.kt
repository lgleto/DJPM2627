package upca.example.topnews

import org.json.JSONObject
import java.util.Date

class Article (
    var title: String? = null,
    var description : String? = null,
    var url : String? = null,
    var urlToImage : String? = null,
    var publishedAt : Date? = null
){

    companion object {

        fun fromJson(value : JSONObject) : Article{
            val title = value.getString("title")
            val description = value.getString("description")
            val url = value.getString("url")
            val urlToImage = value.getString("urlToImage")
            val publishedAt = value.getString("publishedAt")
            val article = Article(
                title = title,
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt.toServerDateTime()
            )
            return article
        }
    }
}


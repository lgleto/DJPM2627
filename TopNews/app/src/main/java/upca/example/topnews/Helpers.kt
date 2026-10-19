package upca.example.topnews

import java.net.URLDecoder
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date

fun String.toServerDateTime(): Date? {
    //2026-10-13T10:32:30Z
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return format.parse(this)
}

fun String.encodeURL(): String {
    return URLEncoder.encode(this, "UTF-8")
}

fun String.decodeURL(): String {
    return URLDecoder.decode(this, "UTF-8")
}

fun Date.toShortDateTime(): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return format.format(this)
}



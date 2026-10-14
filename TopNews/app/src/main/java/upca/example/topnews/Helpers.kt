package upca.example.topnews

import java.text.SimpleDateFormat
import java.util.Date

fun String.toServerDateTime(): Date? {
    //2026-10-13T10:32:30Z
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return format.parse(this)
}
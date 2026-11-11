package upca.example.myshoppinglist.models

import com.google.firebase.firestore.Exclude

data class Kart (
    @get:Exclude
    var id : String? = null,
    var name : String? = null,
    var userIds : List<String>? = null
)
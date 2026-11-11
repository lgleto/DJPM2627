package upca.example.myshoppinglist.models

import com.google.firebase.firestore.Exclude

class User (
    @get:Exclude
    var id : String? = null,
    var name : String? = null,
    var email : String? = null,
)
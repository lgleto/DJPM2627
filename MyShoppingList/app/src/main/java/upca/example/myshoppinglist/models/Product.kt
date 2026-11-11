package upca.example.myshoppinglist.models

import com.google.firebase.firestore.Exclude

class Product (
    @get:Exclude
    var id : String? = null,
    var name : String? = null,
    var quantity : Double? = null,
    var checked : Boolean? = null
)
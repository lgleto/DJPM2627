package upca.example.myshoppinglist.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject
import upca.example.myshoppinglist.TAG
import upca.example.myshoppinglist.models.Kart
import java.io.IOException


data class HomeViewState (
    var karts : List<Kart> = emptyList(),
    var error : String? = null,
    var isLoading : Boolean = false
    )

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeViewState())
    val uiState: StateFlow<HomeViewState> = _uiState.asStateFlow()

    fun fetchKarts () {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error =  null)
        val db = Firebase.firestore
        val currentUser = Firebase.auth.currentUser
        currentUser?.uid?.let { userId ->
            db.collection("karts")
                .whereArrayContains("userIds", userId)
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            error =  e.localizedMessage)
                        return@addSnapshotListener
                    }

                    val karts = ArrayList<Kart>()
                    for (doc in value!!) {
                        val kart = doc.toObject(Kart::class.java)
                        kart.id = doc.id
                        karts.add(kart)
                    }
                    _uiState.value = _uiState.value.copy(
                        karts = karts,
                        isLoading = false,
                        error =  null)
                }
        }

    }
}

package upca.example.myshoppinglist.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import upca.example.myshoppinglist.TAG


data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login(navigateToHome: () -> Unit) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        // Perform login logic here
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(
            _uiState.value.email,
            _uiState.value.password
            )
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null)
                    navigateToHome()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "signInWithEmail:failure ${task.exception?.localizedMessage}")
                }
            }

    }
}


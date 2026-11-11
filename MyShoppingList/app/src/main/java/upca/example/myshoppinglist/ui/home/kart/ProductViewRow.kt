package upca.example.myshoppinglist.ui.home.kart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.myshoppinglist.Screen
import upca.example.myshoppinglist.models.Product
import upca.example.myshoppinglist.ui.theme.MyShoppingListTheme

@Composable
fun ProductViewRow(
    modifier: Modifier = Modifier,
    product: Product,
    onChecked : (Boolean)->Unit = {}
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            product.name ?: "",
            modifier = Modifier
                .weight(1f)
        )
        Text(
            product.quantity.toString() ?: "",
            maxLines = 1
        )
        Checkbox(
            checked = product.checked?:false,
            onCheckedChange = onChecked
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ProductViewPreview() {
    MyShoppingListTheme() {
        ProductViewRow(
            product = Product(
                name = "Canetas",
                quantity = 10.0,
                checked = false
            )
        )
    }
}
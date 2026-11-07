package lat.pam.kopidannyemil.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import lat.pam.kopidannyemil.model.CartItem
import lat.pam.kopidannyemil.model.FoodItem
import lat.pam.kopidannyemil.model.OrderDetails
import java.text.NumberFormat
import java.util.Locale

data class UiState(
    val userName: String = "Nama",
    val userUsername: String = "user123",
    val isLoggedIn: Boolean = false,
    val cartItems: List<CartItem> = emptyList(),
    val activeOrder: OrderDetails? = null,
    val userAddress: String = "",
    val userAddressNotes: String = ""
)

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        // Pre-fill cart with default item (e.g. Kopi Susu Gula Aren) so order screen is ready
        val initialItem = lat.pam.kopidannyemil.model.DummyData.sampleFoodItems.first()
        _uiState.update { currentState ->
            currentState.copy(
                cartItems = listOf(CartItem(item = initialItem, quantity = 1))
            )
        }
    }

    fun login(usernameInput: String, passwordInput: String): Boolean {
        if (usernameInput.isBlank()) return false
        val displayName = if (usernameInput.equals("user", ignoreCase = true)) "User" else usernameInput.replaceFirstChar { it.uppercase() }
        _uiState.update { currentState ->
            currentState.copy(
                userUsername = usernameInput,
                userName = displayName,
                isLoggedIn = true
            )
        }
        return true
    }

    fun register(fullNameInput: String, usernameInput: String, passwordInput: String): Boolean {
        if (fullNameInput.isBlank() || usernameInput.isBlank()) return false
        _uiState.update { currentState ->
            currentState.copy(
                userName = fullNameInput,
                userUsername = usernameInput,
                isLoggedIn = true
            )
        }
        return true
    }

    fun updateProfileName(newName: String) {
        if (newName.isNotBlank()) {
            _uiState.update { currentState ->
                currentState.copy(userName = newName)
            }
        }
    }

    fun addToCart(foodItem: FoodItem) {
        _uiState.update { currentState ->
            val existingIndex = currentState.cartItems.indexOfFirst { it.item.id == foodItem.id }
            val updatedList = if (existingIndex >= 0) {
                currentState.cartItems.mapIndexed { index, cartItem ->
                    if (index == existingIndex) {
                        cartItem.copy(quantity = cartItem.quantity + 1)
                    } else cartItem
                }
            } else {
                currentState.cartItems + CartItem(item = foodItem, quantity = 1)
            }
            currentState.copy(cartItems = updatedList)
        }
    }

    fun removeFromCart(foodItem: FoodItem) {
        _uiState.update { currentState ->
            val existingItem = currentState.cartItems.find { it.item.id == foodItem.id }
            val updatedList = if (existingItem != null && existingItem.quantity > 1) {
                currentState.cartItems.map {
                    if (it.item.id == foodItem.id) it.copy(quantity = it.quantity - 1)
                    else it
                }
            } else {
                currentState.cartItems.filterNot { it.item.id == foodItem.id }
            }
            currentState.copy(cartItems = updatedList)
        }
    }

    fun placeOrder(customerName: String, address: String, notes: String): Boolean {
        if (customerName.isBlank() || address.isBlank()) return false
        val currentCart = _uiState.value.cartItems
        val totalAmount = currentCart.sumOf { it.item.price * it.quantity }
        val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"))
        val formattedTotal = formatter.format(totalAmount).replace(",00", "").replace("Rp", "Rp ")

        val order = OrderDetails(
            customerName = customerName,
            deliveryAddress = address,
            deliveryNotes = notes,
            items = currentCart,
            totalAmount = totalAmount,
            formattedTotal = formattedTotal
        )

        _uiState.update { currentState ->
            currentState.copy(
                userName = customerName,
                userAddress = address,
                userAddressNotes = notes,
                activeOrder = order
            )
        }
        return true
    }

    fun clearActiveOrder() {
        _uiState.update { currentState ->
            // Re-populate with initial sample item for easy re-ordering
            val defaultItem = lat.pam.kopidannyemil.model.DummyData.sampleFoodItems.first()
            currentState.copy(
                cartItems = listOf(CartItem(item = defaultItem, quantity = 1)),
                activeOrder = null
            )
        }
    }
}
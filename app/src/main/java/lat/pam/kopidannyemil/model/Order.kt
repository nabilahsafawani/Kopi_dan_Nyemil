package lat.pam.kopidannyemil.model

data class CartItem(
    val item: FoodItem,
    val quantity: Int
)

data class OrderDetails(
    val customerName: String,
    val deliveryAddress: String,
    val deliveryNotes: String,
    val items: List<CartItem>,
    val totalAmount: Double,
    val formattedTotal: String,
    val orderTimestamp: Long = System.currentTimeMillis()
)
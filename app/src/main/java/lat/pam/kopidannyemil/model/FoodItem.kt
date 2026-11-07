package lat.pam.kopidannyemil.model

data class FoodItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val formattedPrice: String,
    val category: String, // "Makanan" or "Minuman"
    val iconName: String = "coffee"
)

object DummyData {
    val sampleFoodItems = listOf(
        FoodItem(
            id = "1",
            name = "Kopi Susu Gula Aren",
            description = "Espresso, susu, gula aren",
            price = 22000.0,
            formattedPrice = "Rp 22.000",
            category = "Minuman",
            iconName = "coffee"
        ),
        FoodItem(
            id = "2",
            name = "Donat Cokelat",
            description = "Donat empuk, glaze cokelat",
            price = 12000.0,
            formattedPrice = "Rp 12.000",
            category = "Makanan",
            iconName = "donut"
        ),
        FoodItem(
            id = "3",
            name = "Sourdough Loaf",
            description = "Roti fermentasi alami",
            price = 35000.0,
            formattedPrice = "Rp 35.000",
            category = "Makanan",
            iconName = "bread"
        ),
        FoodItem(
            id = "4",
            name = "Cappuccino",
            description = "Espresso, foam susu lembut",
            price = 25000.0,
            formattedPrice = "Rp 25.000",
            category = "Minuman",
            iconName = "coffee"
        ),
        FoodItem(
            id = "5",
            name = "Matcha Latte",
            description = "Matcha jepang, susu segar",
            price = 27000.0,
            formattedPrice = "Rp 27.000",
            category = "Minuman",
            iconName = "tea"
        ),
        FoodItem(
            id = "6",
            name = "Croissant Butter",
            description = "Pastry renyah dengan mentega gurih",
            price = 20000.0,
            formattedPrice = "Rp 20.000",
            category = "Makanan",
            iconName = "croissant"
        ),
        FoodItem(
            id = "7",
            name = "Americano Cold Brew",
            description = "Kopi hitam dingin menyegarkan",
            price = 18000.0,
            formattedPrice = "Rp 18.000",
            category = "Minuman",
            iconName = "coffee"
        )
    )
}
package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import lat.pam.kopidannyemil.model.DummyData
import lat.pam.kopidannyemil.model.FoodItem
import lat.pam.kopidannyemil.ui.components.BottomNavBar
import lat.pam.kopidannyemil.ui.navigation.Screen
import lat.pam.kopidannyemil.ui.theme.CoffeeAccent
import lat.pam.kopidannyemil.ui.theme.CoffeeOnPrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurface
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurfaceVariant
import lat.pam.kopidannyemil.ui.theme.CoffeePrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeSecondaryContainer
import lat.pam.kopidannyemil.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: AppViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedCategory by remember { mutableStateOf("Semua") }

    val filteredItems = remember(selectedCategory) {
        if (selectedCategory == "Semua") DummyData.sampleFoodItems
        else DummyData.sampleFoodItems.filter { it.category == selectedCategory }
    }

    val totalCartCount = uiState.cartItems.sumOf { it.quantity }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Screen Header Label
            Text(
                text = "5. Home",
                style = MaterialTheme.typography.labelMedium,
                color = CoffeeOnSurfaceVariant.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Greeting
            Text(
                text = "Halo ${uiState.userName},",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = CoffeeOnSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Section Title
            Text(
                text = "Makanan & Minuman",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = CoffeeOnSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Category Filter Chips
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("Semua", "Makanan", "Minuman").forEach { category ->
                    val isSelected = selectedCategory == category
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCategory = category },
                        label = { Text(category) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = CoffeePrimary,
                            selectedLabelColor = CoffeeOnPrimary,
                            containerColor = CoffeeSecondaryContainer,
                            labelColor = CoffeeOnSurface
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Food Item List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(filteredItems) { item ->
                    val cartItem = uiState.cartItems.find { it.item.id == item.id }
                    val itemQuantity = cartItem?.quantity ?: 0

                    FoodItemCard(
                        item = item,
                        quantity = itemQuantity,
                        onAdd = { viewModel.addToCart(item) },
                        onRemove = { viewModel.removeFromCart(item) }
                    )
                }
            }

            // Cart Bar if items are added
            if (totalCartCount > 0) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = CoffeePrimary,
                    shadowElevation = 6.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.ShoppingBag,
                                contentDescription = "Cart",
                                tint = CoffeeOnPrimary
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "$totalCartCount item dipilih",
                                color = CoffeeOnPrimary,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        }

                        Button(
                            onClick = { navController.navigate(Screen.AddressOrder.route) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CoffeeAccent,
                                contentColor = CoffeeOnSurface
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "Lanjut Order",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FoodItemCard(
    item: FoodItem,
    quantity: Int,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CoffeeSecondaryContainer.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Photo / Icon Box matching Wireframe ("Foto")
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CoffeeAccent.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val icon = when (item.category) {
                        "Makanan" -> Icons.Default.BakeryDining
                        else -> Icons.Default.Coffee
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = item.name,
                        tint = CoffeePrimary,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = "Foto",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = CoffeePrimary
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = CoffeeOnSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "• ${item.description}",
                    fontSize = 13.sp,
                    color = CoffeeOnSurfaceVariant
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = item.formattedPrice,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CoffeePrimary
                )
            }

            // Quantity selector
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (quantity > 0) {
                    IconButton(
                        onClick = onRemove,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Kurangi",
                            tint = CoffeePrimary
                        )
                    }
                    Text(
                        text = "$quantity",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                }
                IconButton(
                    onClick = onAdd,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tambah",
                        tint = CoffeePrimary
                    )
                }
            }
        }
    }
}
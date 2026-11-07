package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import lat.pam.kopidannyemil.ui.components.BottomNavBar
import lat.pam.kopidannyemil.ui.navigation.Screen
import lat.pam.kopidannyemil.ui.theme.CoffeeOnPrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurface
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurfaceVariant
import lat.pam.kopidannyemil.ui.theme.CoffeePrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeSecondaryContainer
import lat.pam.kopidannyemil.viewmodel.AppViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AddressOrderScreen(
    navController: NavController,
    viewModel: AppViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    var namaLengkap by remember(uiState.userName) { mutableStateOf(uiState.userName) }
    var alamat by remember(uiState.userAddress) { mutableStateOf(uiState.userAddress) }
    var patokan by remember(uiState.userAddressNotes) { mutableStateOf(uiState.userAddressNotes) }
    var errorMessage by remember { mutableStateOf("") }

    val totalAmount = uiState.cartItems.sumOf { it.item.price * it.quantity }
    val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"))
    val formattedTotal = formatter.format(totalAmount).replace(",00", "").replace("Rp", "Rp ")

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Screen Header Label
                Text(
                    text = "7. Alamat pengiriman (Order)",
                    style = MaterialTheme.typography.labelMedium,
                    color = CoffeeOnSurfaceVariant.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Header title: Alamat
                Text(
                    text = "Alamat",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = CoffeeOnSurface
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Order summary card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = CoffeeSecondaryContainer.copy(alpha = 0.6f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Ringkasan Pesanan",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = CoffeeOnSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        if (uiState.cartItems.isEmpty()) {
                            Text(
                                text = "Belum ada item yang dipilih",
                                color = CoffeeOnSurfaceVariant,
                                fontSize = 14.sp
                            )
                        } else {
                            uiState.cartItems.forEach { cartItem ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 2.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${cartItem.item.name} (${cartItem.quantity}x)",
                                        fontSize = 14.sp,
                                        color = CoffeeOnSurface
                                    )
                                    Text(
                                        text = cartItem.item.formattedPrice,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = CoffeePrimary
                                    )
                                }
                            }
                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total Pembayaran",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = CoffeeOnSurface
                                )
                                Text(
                                    text = formattedTotal,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = CoffeePrimary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Field 1: Nama Lengkap
                OutlinedTextField(
                    value = namaLengkap,
                    onValueChange = {
                        namaLengkap = it
                        errorMessage = ""
                    },
                    label = { Text("Nama Lengkap") },
                    placeholder = { Text("Masukkan Nama Lengkap") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Field 2: Alamat
                OutlinedTextField(
                    value = alamat,
                    onValueChange = {
                        alamat = it
                        errorMessage = ""
                    },
                    label = { Text("Alamat") },
                    placeholder = { Text("Masukkan Alamat Pengiriman") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Field 3: Patokan atau detail lengkap
                OutlinedTextField(
                    value = patokan,
                    onValueChange = { patokan = it },
                    label = { Text("Patokan atau detail lengkap") },
                    placeholder = { Text("Contoh: Rumah cat hijau dekat masjid") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 3
                )

                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Button: Order dan Kirim matching wireframe
            Button(
                onClick = {
                    if (namaLengkap.isBlank() || alamat.isBlank()) {
                        errorMessage = "Nama lengkap & alamat pengiriman wajib diisi"
                    } else if (uiState.cartItems.isEmpty()) {
                        errorMessage = "Silakan pilih minimal 1 menu makanan/minuman"
                    } else {
                        val success = viewModel.placeOrder(namaLengkap, alamat, patokan)
                        if (success) {
                            navController.navigate(Screen.OrderConfirmation.route)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CoffeePrimary,
                    contentColor = CoffeeOnPrimary
                )
            ) {
                Text(
                    text = "Order dan Kirim",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
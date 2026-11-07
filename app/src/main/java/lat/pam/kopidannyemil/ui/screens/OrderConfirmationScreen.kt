package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import lat.pam.kopidannyemil.ui.navigation.Screen
import lat.pam.kopidannyemil.ui.theme.CoffeeOnPrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurface
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurfaceVariant
import lat.pam.kopidannyemil.ui.theme.CoffeePrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeSecondaryContainer
import lat.pam.kopidannyemil.ui.theme.SuccessGreen
import lat.pam.kopidannyemil.ui.theme.SuccessGreenBadge
import lat.pam.kopidannyemil.viewmodel.AppViewModel

@Composable
fun OrderConfirmationScreen(
    navController: NavController,
    viewModel: AppViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val order = uiState.activeOrder

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header label
                Text(
                    text = "8. Konfirmasi pesanan",
                    style = MaterialTheme.typography.labelMedium,
                    color = CoffeeOnSurfaceVariant.copy(alpha = 0.6f),
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Greeting Header matching wireframe
                Text(
                    text = "Halo ${uiState.userName},",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = CoffeeOnSurface,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Green check circle icon ("OK") matching wireframe design
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(SuccessGreenBadge),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Success",
                            tint = SuccessGreen,
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = "OK",
                            fontWeight = FontWeight.Bold,
                            color = SuccessGreen,
                            fontSize = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Subtext matching wireframe exactly
                Text(
                    text = "Terima kasih sudah memesan! Tunggu aja sebentar, kurir kami segera mengantarkan pesanamu!",
                    fontSize = 16.sp,
                    color = CoffeeOnSurface,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Order details card if available
                if (order != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CoffeeSecondaryContainer.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Text(
                                text = "Detail Pengiriman",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = CoffeePrimary
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Penerima: ${order.customerName}",
                                fontSize = 14.sp,
                                color = CoffeeOnSurface
                            )
                            Text(
                                text = "Alamat: ${order.deliveryAddress}",
                                fontSize = 14.sp,
                                color = CoffeeOnSurfaceVariant
                            )
                            if (order.deliveryNotes.isNotBlank()) {
                                Text(
                                    text = "Patokan: ${order.deliveryNotes}",
                                    fontSize = 13.sp,
                                    color = CoffeeOnSurfaceVariant
                                )
                            }
                            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                            Text(
                                text = "Pesanan:",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = CoffeeOnSurface
                            )
                            order.items.forEach { cartItem ->
                                Text(
                                    text = "• ${cartItem.item.name} (${cartItem.quantity}x) - ${cartItem.item.formattedPrice}",
                                    fontSize = 13.sp,
                                    color = CoffeeOnSurfaceVariant
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total Bayar",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = CoffeeOnSurface
                                )
                                Text(
                                    text = order.formattedTotal,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = CoffeePrimary
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Oke Button matching wireframe
            Button(
                onClick = {
                    viewModel.clearActiveOrder()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CoffeePrimary,
                    contentColor = CoffeeOnPrimary
                )
            ) {
                Text(
                    text = "Oke",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import lat.pam.kopidannyemil.ui.navigation.Screen
import lat.pam.kopidannyemil.ui.theme.CoffeeAccent
import lat.pam.kopidannyemil.ui.theme.CoffeeOnPrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurface
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurfaceVariant
import lat.pam.kopidannyemil.ui.theme.CoffeePrimary

@Composable
fun SplashScreen(navController: NavController) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header tag
            Text(
                text = "1. Splash / Welcome",
                style = MaterialTheme.typography.labelMedium,
                color = CoffeeOnSurfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.align(Alignment.Start)
            )

            // Center content with logo and app name
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                // Circle logo container
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(CoffeeAccent.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocalCafe,
                            contentDescription = "Logo",
                            modifier = Modifier.size(56.dp),
                            tint = CoffeePrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Logo",
                            fontWeight = FontWeight.SemiBold,
                            color = CoffeePrimary,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(36.dp))

                Text(
                    text = "Kopi dan Nyemil",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = CoffeeOnSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Get a taste of your wish",
                    fontSize = 16.sp,
                    color = CoffeeOnSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }

            // Bottom CTA Button
            Button(
                onClick = { navController.navigate(Screen.SignUpOptions.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CoffeePrimary,
                    contentColor = CoffeeOnPrimary
                )
            ) {
                Text(
                    text = "Start now",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
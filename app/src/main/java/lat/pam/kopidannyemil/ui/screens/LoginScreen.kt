package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import lat.pam.kopidannyemil.ui.navigation.Screen
import lat.pam.kopidannyemil.ui.theme.CoffeeOnPrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurface
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurfaceVariant
import lat.pam.kopidannyemil.ui.theme.CoffeePrimary
import lat.pam.kopidannyemil.viewmodel.AppViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AppViewModel
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Screen Header Label
            Text(
                text = "3. Login User",
                style = MaterialTheme.typography.labelMedium,
                color = CoffeeOnSurfaceVariant.copy(alpha = 0.6f)
            )

            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Login User",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = CoffeeOnSurface
                )

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        errorMessage = ""
                    },
                    label = { Text("username") },
                    placeholder = { Text("Masukkan username") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        errorMessage = ""
                    },
                    label = { Text("password") },
                    placeholder = { Text("Masukkan password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = { navController.navigate(Screen.Register.route) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "Belum punya akun? Register",
                        color = CoffeePrimary
                    )
                }
            }

            // Bottom CTA Button
            Button(
                onClick = {
                    if (username.isBlank()) {
                        errorMessage = "Username tidak boleh kosong"
                    } else {
                        viewModel.login(username, password)
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
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
                    text = "Login",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
package lat.pam.kopidannyemil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import lat.pam.kopidannyemil.ui.components.BottomNavBar
import lat.pam.kopidannyemil.ui.theme.CoffeeOnPrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurface
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurfaceVariant
import lat.pam.kopidannyemil.ui.theme.CoffeePrimary
import lat.pam.kopidannyemil.viewmodel.AppViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: AppViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var nameInput by remember(uiState.userName) { mutableStateOf(uiState.userName) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = { BottomNavBar(navController = navController) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Screen Header Label
                Text(
                    text = "6. Profile",
                    style = MaterialTheme.typography.labelMedium,
                    color = CoffeeOnSurfaceVariant.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Greeting Header matching wireframe
                Text(
                    text = "Halo ${uiState.userName},",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = CoffeeOnSurface
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Section: Profile Saya
                Text(
                    text = "Profile Saya",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = CoffeeOnSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Sub-section: Profile Setting
                Text(
                    text = "Profile Setting",
                    fontSize = 16.sp,
                    color = CoffeeOnSurfaceVariant
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Input Field: nama
                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    label = { Text("nama") },
                    placeholder = { Text("Masukkan nama baru") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            // Save Button matching wireframe
            Button(
                onClick = {
                    viewModel.updateProfileName(nameInput)
                    scope.launch {
                        snackbarHostState.showSnackbar("Nama profil berhasil disimpan!")
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
                    text = "Save",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
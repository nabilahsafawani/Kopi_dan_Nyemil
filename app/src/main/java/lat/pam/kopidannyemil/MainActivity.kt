package lat.pam.kopidannyemil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lat.pam.kopidannyemil.ui.navigation.Screen
import lat.pam.kopidannyemil.ui.screens.AddressOrderScreen
import lat.pam.kopidannyemil.ui.screens.HomeScreen
import lat.pam.kopidannyemil.ui.screens.LoginScreen
import lat.pam.kopidannyemil.ui.screens.OrderConfirmationScreen
import lat.pam.kopidannyemil.ui.screens.ProfileScreen
import lat.pam.kopidannyemil.ui.screens.RegisterScreen
import lat.pam.kopidannyemil.ui.screens.SignUpOptionsScreen
import lat.pam.kopidannyemil.ui.screens.SplashScreen
import lat.pam.kopidannyemil.ui.theme.KopiDanNyemilTheme
import lat.pam.kopidannyemil.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KopiDanNyemilTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    KopiDanNyemilApp(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun KopiDanNyemilApp(viewModel: AppViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.SignUpOptions.route) {
            SignUpOptionsScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.AddressOrder.route) {
            AddressOrderScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.OrderConfirmation.route) {
            OrderConfirmationScreen(navController = navController, viewModel = viewModel)
        }
    }
}
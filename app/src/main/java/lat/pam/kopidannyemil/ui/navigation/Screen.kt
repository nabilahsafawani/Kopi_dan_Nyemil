package lat.pam.kopidannyemil.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object SignUpOptions : Screen("sign_up_options")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object AddressOrder : Screen("address_order")
    object OrderConfirmation : Screen("order_confirmation")
}
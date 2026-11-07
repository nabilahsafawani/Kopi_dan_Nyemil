package lat.pam.kopidannyemil.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import lat.pam.kopidannyemil.ui.navigation.Screen
import lat.pam.kopidannyemil.ui.theme.CoffeeOnPrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeOnSurface
import lat.pam.kopidannyemil.ui.theme.CoffeePrimary
import lat.pam.kopidannyemil.ui.theme.CoffeeSecondaryContainer

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(Screen.Home.route, "Home", Icons.Default.Home)
    object Order : BottomNavItem(Screen.AddressOrder.route, "Order", Icons.Default.ShoppingBag)
    object Profile : BottomNavItem(Screen.Profile.route, "Profile", Icons.Default.Person)
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Order,
        BottomNavItem.Profile
    )
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = CoffeeSecondaryContainer
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CoffeeOnPrimary,
                    selectedTextColor = CoffeePrimary,
                    indicatorColor = CoffeePrimary,
                    unselectedIconColor = CoffeeOnSurface,
                    unselectedTextColor = CoffeeOnSurface
                )
            )
        }
    }
}
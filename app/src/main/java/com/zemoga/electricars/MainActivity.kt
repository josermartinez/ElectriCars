package com.zemoga.electricars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zemoga.electricars.presentation.car_details.CarDetailsScreen
import com.zemoga.electricars.presentation.car_listing.CarListScreen
import com.zemoga.electricars.ui.theme.ElectriCarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElectriCarsTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var showNavigationIcon by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    navController.addOnDestinationChangedListener { controller, _, _ ->
        showNavigationIcon = controller.previousBackStackEntry != null
    }

    Scaffold(modifier = modifier, topBar = {
        MainTopAppBar(
            title = stringResource(id = R.string.app_name),
            showNavigationIcon = showNavigationIcon,
            onBackIconClicked = {
                navController.navigateUp()
            }
        )
    }) { padding ->
        ElectriCarsNavHost(Modifier.padding(padding), navController)
    }
}

@Composable
fun ElectriCarsNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ElectriCarScreens.CAR_LISTING.name,
        modifier = modifier
    ) {
        composable(ElectriCarScreens.CAR_LISTING.name) {
            CarListScreen(navController = navController)
        }
        composable(
            route = "${ElectriCarScreens.CAR_DETAILS.name}/{carId}",
            arguments = listOf(navArgument("carId") {
                type = NavType.StringType
            })
        ) { entry ->
            val carId = entry.arguments?.getString("carId").orEmpty()
            CarDetailsScreen(carId = carId)
        }
    }
}

@Composable
fun MainTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    showNavigationIcon: Boolean = false,
    onBackIconClicked: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color.Cyan,
        title = {
            Text(text = title)
        }, navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = onBackIconClicked) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

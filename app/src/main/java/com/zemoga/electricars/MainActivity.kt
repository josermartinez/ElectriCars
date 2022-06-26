package com.zemoga.electricars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.EvStation
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zemoga.electricars.presentation.car_details.CarDetailsScreen
import com.zemoga.electricars.presentation.car_listing.CarListScreen
import com.zemoga.electricars.presentation.station_details.StationDetailsScreen
import com.zemoga.electricars.presentation.station_listing.StationListingScreen
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

    Scaffold(modifier = modifier,
        topBar = {
            MainTopAppBar(
                title = stringResource(id = R.string.app_name),
                showNavigationIcon = showNavigationIcon,
                onBackIconClicked = {
                    navController.navigateUp()
                }
            )
        },
        bottomBar = {
            var selectedScreen by rememberSaveable {
                mutableStateOf(ElectriCarScreens.CAR_LISTING)
            }

            ElectriCarsBottomNavigation(selectedScreen = selectedScreen,
                onClick = {
                    if (navController.currentBackStackEntry?.destination?.route?.contains(it.name) == false){
                        when (it) {
                            ElectriCarScreens.CAR_LISTING -> {
                                navController.navigate("${ElectriCarScreens.CAR_LISTING}")
                                selectedScreen = ElectriCarScreens.CAR_LISTING
                            }
                            ElectriCarScreens.STATION_LISTING -> {
                                navController.navigate("${ElectriCarScreens.STATION_LISTING}")
                                selectedScreen = ElectriCarScreens.STATION_LISTING
                            }
                        }
                    }
                })
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
        composable(route = ElectriCarScreens.STATION_LISTING.name) {
            StationListingScreen(navController = navController)
        }
        composable(
            route = "${ElectriCarScreens.STATION_DETAILS.name}/{stationId}",
            arguments = listOf(navArgument("stationId") {
                type = NavType.StringType
            })
        ) { entry ->
            val stationId = entry.arguments?.getString("stationId").orEmpty()
            StationDetailsScreen(stationId = stationId)
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

@Composable
fun ElectriCarsBottomNavigation(
    modifier: Modifier = Modifier,
    selectedScreen: ElectriCarScreens = ElectriCarScreens.CAR_LISTING,
    onClick: (ElectriCarScreens) -> Unit = {}
) {
    BottomNavigation(modifier = modifier) {
        BottomNavigationItem(
            selected = selectedScreen == ElectriCarScreens.CAR_LISTING,
            onClick = { onClick(ElectriCarScreens.CAR_LISTING) },
            icon = {
                Icon(
                    imageVector = Icons.Default.ElectricCar,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.bottom_navigation_electric_cars_label)
                )
            })

        BottomNavigationItem(
            selected = selectedScreen == ElectriCarScreens.STATION_LISTING,
            onClick = { onClick(ElectriCarScreens.STATION_LISTING) },
            icon = {
                Icon(
                    imageVector = Icons.Default.EvStation,
                    contentDescription = null
                )
            }, label = {
                Text(
                    text = stringResource(id = R.string.bottom_navigation_stations_label)
                )
            })
    }
}

@Preview
@Composable
fun ElectriCarsBottomNavigationPreview() {
    ElectriCarsBottomNavigation()
}


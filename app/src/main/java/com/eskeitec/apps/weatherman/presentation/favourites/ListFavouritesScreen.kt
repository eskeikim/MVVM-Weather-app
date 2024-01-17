package com.eskeitec.apps.weatherman.presentation.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eskeitec.apps.weatherman.data.datasource.local.entity.LocationEntity
import com.eskeitec.apps.weatherman.presentation.Screen
import com.eskeitec.apps.weatherman.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFavouritesScreen(
    navController: NavHostController,
    favouritesViewModel: FavouritesViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Favourite Locations") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack(Screen.Home.name, false)
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "back navigation icon",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                ),
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddLocation.name)
            }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        },
    ) { innerPaddding ->
        Column(
            modifier = Modifier
                .padding(innerPaddding),

        ) {
            val locations = favouritesViewModel.favourites.value
//
            if (locations.isNullOrEmpty()) {
                Text(
                    text = Constants.EMPTY_FAVOURITES,
                    modifier = Modifier.fillMaxSize().padding(30.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                )
            } else {
                Box(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(locations) { item ->
                            FavouriteItem(item, navController, favouritesViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavouriteItem(
    item: LocationEntity,
    navController: NavHostController,
    favouritesViewModel: FavouritesViewModel,
) {
//    val isFavourite = favouritesViewModel.isFavouriteAdded.value
    val isFavourite = true
    val latlon = item.latlog
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background(color = Color.White).clickable {
                navController.navigate("${Screen.ViewWeatherScreen.name}/$latlon")
            },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${item.cityName}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                ),
                color = Color.Black,
            )

            Text(
                text = "${item.temp}º",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                ),
                color = Color.Black,
            )
            IconButton(
                onClick = {
                    if (isFavourite == true) {
                        favouritesViewModel.isFavouriteRemoved(item)
                    } else {
                        favouritesViewModel.addLocationToFavourite(
                            item,
                        )
                    }
                },
            ) {
                if (isFavourite == true) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Add to favourite",
                        modifier = Modifier
                            .size(100.dp),
                        tint = Color.Red,
                    )
                } else {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = "Add to favourite",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(100.dp),
                    )
                }
            }
        }
    }
}

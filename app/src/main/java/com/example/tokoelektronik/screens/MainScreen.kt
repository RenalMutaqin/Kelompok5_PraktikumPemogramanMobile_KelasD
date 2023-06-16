package com.example.tokoelektronik.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tokoelektronik.ui.theme.Purple700
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val title = remember { mutableStateOf("") }
    val appBarHorizontalPadding = 4.dp
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple700,
                elevation = 0.dp,
                modifier= Modifier.fillMaxWidth())
            {
                //TopAppBar Content
                Box(Modifier.height(32.dp)) {
                    Row(
                        Modifier.fillMaxHeight()
                        .width(72.dp - appBarHorizontalPadding),
                        verticalAlignment = Alignment.CenterVertically) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides
                                    ContentAlpha.high,
                        ) {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        scaffoldState.drawerState.open()
                                    }
                                },
                                enabled = true,
                            ) {
                                Icon(
                                    Icons.Filled.Menu, null,
                                    tint = Color.White)
                            }
                        }
                    }
                    //  Title
                    Row(
                        Modifier.fillMaxSize(),
                        verticalAlignment =
                        Alignment.CenterVertically) {
                        ProvideTextStyle(value =
                        MaterialTheme.typography.h6) {
                            CompositionLocalProvider(
                                LocalContentAlpha provides
                                        ContentAlpha.high,
                            ){
                                Text(
                                    modifier =
                                    Modifier.fillMaxWidth(),
                                    textAlign =
                                    TextAlign.Center,
                                    color = Color.White,
                                    maxLines = 1,
                                    text = title.value
                                )
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
        // reuse default SnackbarHost to have default  animation and timing handling
            SnackbarHost(it) { data ->
                // custom snackbar with the custom colors
                Snackbar(
                    actionColor = Color.Green,
                    contentColor = Color.White,
                    snackbarData = data
                )
            }
        },
        drawerContent = {
            DrawerContent { route ->
                navController.navigate(route)
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },
        bottomBar = {
            BottomNavigationComposable(title.value, onClick = { tab ->
                navController.navigate(tab.route)
            })
        },
    )
    { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    title.value = "Home"
                    HomeScreen()
                }
                composable("dosen") {
                    title.value = "Dosen"
                    KomputerScreen(navController = navController,
                        snackbarHostState = scaffoldState.snackbarHostState, modifier =
                        Modifier.padding(innerPadding))
                }
                composable("tambah-dosen") {
                    title.value = "Tambah Dosen"
                    FormKomputerScreen(navController =
                    navController, modifier = Modifier.padding(innerPadding))
                }
                composable("edit-dosen/{id}",
                    listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { backStackEntry ->
                    title.value = "Edit Dosen"
                    val id =
                        backStackEntry.arguments?.getString("id")
                            ?: return@composable
                    FormKomputerScreen(navController = navController, id = id, modifier =
                    Modifier.padding(innerPadding))
                }
//route
                composable("mahasiswa") {
                    title.value = "Mahasiswa"
                    SmartphoneScreen(navController = navController,
                        snackbarHostState = scaffoldState.snackbarHostState, modifier =
                        Modifier.padding(innerPadding))
                }
                composable("tambah-smarthpone") {
                    title.value = "Tambah smartphone"
                    FormSmartphoneScreen(navController =
                    navController, modifier = Modifier.padding(innerPadding))
                }
                composable("edit-mahasiswa/{id}",
                    listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { backStackEntry ->
                    title.value = "Edit Mahasiswa"
                    val id =
                        backStackEntry.arguments?.getString("id")
                            ?: return@composable
                    FormKomputerScreen(navController = navController, id = id, modifier =
                    Modifier.padding(innerPadding))
                }
//                route matkul
                composable("matkul") {
                    title.value = "Matakuliah"
                    SmartphoneScreen(navController = navController,
                        snackbarHostState = scaffoldState.snackbarHostState, modifier =
                        Modifier.padding(innerPadding))
                }
                composable("tambah-matkul") {
                    title.value = "Tambah Matakuliah"
                    FormSmartphoneScreen(navController =
                    navController, modifier = Modifier.padding(innerPadding))
                }
                composable("edit-matkul/{id}",
                    listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { backStackEntry ->
                    title.value = "Edit Matakuliah"
                    val id =
                        backStackEntry.arguments?.getString("id")
                            ?: return@composable
                    FormSmartphoneScreen(navController = navController, id = id, modifier =
                    Modifier.padding(innerPadding))
                }
            }
        }
    }
}
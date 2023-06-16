package ppm.b.kelompok4.tokoelektronik.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import ppm.b.kelompok4.tokoelektronik.ui.theme.Purple700
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
                modifier= Modifier.fillMaxWidth()) {
                //TopAppBar Content
                Box(Modifier.height(32.dp)) {
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(72.dp -
                                    appBarHorizontalPadding), verticalAlignment =
                        Alignment.CenterVertically) {
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
                                Icon(Icons.Filled.Menu, null,
                                    tint = Color.White)
                            }}
                    }
                    //Title
                    Row(Modifier.fillMaxSize(),
                        verticalAlignment =
                        Alignment.CenterVertically) {
                        ProvideTextStyle(value =
                        MaterialTheme.typography.h6) {
                            CompositionLocalProvider(
                                LocalContentAlpha provides
                                        ContentAlpha.high,
                            )
                            {
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
            // reuse default SnackbarHost to have default animation and timing handling
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
            BottomNavigationComposable(title.value, onClick =
            { tab ->
                navController.navigate(tab.route)
            })
        },
    )
    { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHost(navController = navController,
                startDestination = "pengelolaan-komputer") {

                composable("about") {
                    title.value = "About Cloudtech"
                    AboutScreen(modifier = Modifier.padding(innerPadding))
                }

                composable("pengelolaan-smartphone") {
                    title.value = "Pengelolaan Smartphone"
                    PengelolaanSmartphoneScreen(navController =
                    navController, snackbarHostState =
                    scaffoldState.snackbarHostState, modifier =
                    Modifier.padding(innerPadding))
                }

                composable("pengelolaan-komputer") {
                    title.value = "Pengelolaan Komputer"
                    PengelolaanKomputerScreen(navController =
                    navController, snackbarHostState =
                    scaffoldState.snackbarHostState, modifier =
                    Modifier.padding(innerPadding))
                }

                composable("pengelolaan-periferal") {
                    title.value = "Pengelolaan Periferal"
                    PengelolaanPeriferalScreen(navController =
                    navController, snackbarHostState =
                    scaffoldState.snackbarHostState, modifier =
                    Modifier.padding(innerPadding))
                }

                composable("tambah-pengelolaan-smartphone") {
                    title.value = "Tambah Data Smartphone"
                    FormPencatatanSmartphone(navController =
                    navController, modifier = Modifier.padding(innerPadding))
                }

                composable("tambah-pengelolaan-komputer") {
                    title.value = "Tambah Data Komputer"
                    FormPencatatanKomputer(navController =
                    navController, modifier = Modifier.padding(innerPadding))
                }

                composable("tambah-pengelolaan-periferal") {
                    title.value = "Tambah Data Periferal"
                    FormPencatatanPeriferal(navController =
                    navController, modifier = Modifier.padding(innerPadding))
                }

                composable("edit-pengelolaan-smartphone/{id}",
                    listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { backStackEntry ->
                    title.value = "Edit Pengelolaan Smartphone"
                    val id =
                backStackEntry.arguments?.getString("id")
                    ?: return@composable
                    FormPencatatanSmartphone(navController =
                navController, id = id, modifier =
                Modifier.padding(innerPadding))
                }

                composable("edit-pengelolaan-komputer/{id}",
                    listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { backStackEntry ->
                    title.value = "Edit Pengelolaan Komputer"
                    val id =
                        backStackEntry.arguments?.getString("id")
                            ?: return@composable
                    FormPencatatanKomputer(navController =
                    navController, id = id, modifier =
                    Modifier.padding(innerPadding))
                }

                composable("edit-pengelolaan-periferal/{id}",
                    listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { backStackEntry ->
                    title.value = "Edit Pengelolaan Periferal"
                    val id =
                        backStackEntry.arguments?.getString("id")
                            ?: return@composable
                    FormPencatatanPeriferal(navController =
                    navController, id = id, modifier =
                    Modifier.padding(innerPadding))
                }
        }
    }
}
}

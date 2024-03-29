package com.avtelma.tagaevplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avtelma.tagaevplanner.navigation.Screen
import com.avtelma.tagaevplanner.ui.MainViewModel
import com.avtelma.tagaevplanner.ui.screens.addtask.AddTaskProgressor
import com.avtelma.tagaevplanner.ui.screens.main.MainSphereScreen
import com.avtelma.tagaevplanner.ui.screens.progresser.ProgresserScreen
import com.avtelma.tagaevplanner.ui.theme.TagaevPlannerTheme

var pro = arrayListOf<Float>(1f,1f,1f,1f,1f,1f,11f,23f)
var visibleListOfTasks = mutableStateOf<Boolean>(true)

class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            TagaevPlannerTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val scaffoldState = rememberScaffoldState()
                    val navController = rememberNavController()

                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(androidx.compose.ui.graphics.Color.Magenta),
                        scaffoldState = scaffoldState,
                        topBar = {
                            //TopBar(navController)
                        },
                        content = {
                            NavigationScreen(navController, it)
                        },
                        bottomBar = {
                            //BottomBar(navController)
                        }
                    )

                }
            }
        }
    }

    @Composable
    fun NavigationScreen(
        navController: NavHostController,
        paddingValues: PaddingValues
    ) {
        // create vm by factory
        var mainViewModel = hiltViewModel<MainViewModel>()


        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = Screen.MainSphereScreen.route
            ) {
                composable(
                    route = Screen.MainSphereScreen.route
                ) {
                    MainSphereScreen(mainViewModel, navController)
                }

                composable(
                    route = Screen.ProgresserScreen.route
                ) {
                    ProgresserScreen(mainViewModel, navController)
                }

                composable(
                    route = Screen.AddTaskScreen.route
                ) {
                    AddTaskProgressor(navController,mainViewModel)
                }
            }
//            if (mainViewModel.isShowingDialog.value) {
//                listOfCurrencies()
//            }
        }
    }


}




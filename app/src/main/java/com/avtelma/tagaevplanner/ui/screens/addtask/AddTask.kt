package com.avtelma.tagaevplanner.ui.screens.addtask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavHostController
import com.avtelma.tagaevplanner.R
import com.avtelma.tagaevplanner.navigation.Screen
import com.avtelma.tagaevplanner.ui.MainViewModel


@Composable
fun AddTaskProgressor(navController: NavHostController, mainViewModel: MainViewModel) {
    val gradient45 = Brush.linearGradient(
        colors = listOf(Color.Black, Color.DarkGray),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )
//    BackHandler {
//        // your action
//        visibleListOfTasks.value = true
//    }
    var textState = remember { mutableStateOf("") }

    val checkedState = remember { mutableStateOf(true) }
    val textColor = remember { mutableStateOf(Color.Green) }
    val textSwitcher = remember { mutableStateOf("Is Progresser") }

    Column(
        Modifier
            .fillMaxSize()
            .background(gradient45)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly) {
        Text(
            text = "Write here task:",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(10.dp),
            fontSize = 30.sp,fontFamily = FontFamily(
                ResourcesCompat.getFont(
                    LocalContext.current, R.font.rubik_regular)!!)
        )
        TextField(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(20.dp),
            value = textState.value,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Color.White,
                textColor = Color.White,
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Gray),
            onValueChange = {
                textState.value = it
            },
            //label = { Text("Enter text") },
            //maxLines = 2,
            textStyle = TextStyle(
                color = Color.White, fontWeight = FontWeight.Bold, //fontSize = TextUnit(30f, TextUnitType.Sp)
            ),
        )

        Row (
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
        ){
            Text(textSwitcher.value, fontSize = 22.sp, color = textColor.value)
            Switch(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    if(checkedState.value) {
                        textColor.value = Color.Green
                        textSwitcher.value = "Is Progresser"
                    }
                    else {
                        textColor.value = Color.LightGray
                        textSwitcher.value = "Is CopyPaster"
                    }
                }
            )
        }

        OutlinedButton(modifier = Modifier.fillMaxWidth().height(150.dp)
            .padding(30.dp),
            onClick = {
                navController.navigate(Screen.ProgresserScreen.route)
            }
        ) {
            Text("Save task",color = Color.Black)
        }
    }
}

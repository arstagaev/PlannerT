package com.avtelma.tagaevplanner.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.avtelma.tagaevplanner.visibleListOfTasks



@Composable
fun addTaskProgressor() {
    val gradient45 = Brush.linearGradient(
        colors = listOf(Color.Black, Color.DarkGray),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )
    BackHandler {
        // your action
        visibleListOfTasks.value = true
    }
    var textState = remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize().background(gradient45)) {

        TextField(
            modifier = Modifier.fillMaxWidth().height(90.dp).padding(20.dp),
            value = textState.value,
            onValueChange = { textState.value = it
            },
            //label = { Text("Enter text") },
            //maxLines = 2,
            textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, //fontSize = TextUnit(30f, TextUnitType.Sp)
            ),

        )

        OutlinedButton(modifier = Modifier.fillMaxWidth().height(150.dp)
            .align(Alignment.BottomCenter).padding(30.dp), onClick = {

        }) {
            Text("Save task")
        }
    }
}
package com.avtelma.tagaevplanner.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavHostController
import com.avtelma.tagaevplanner.Holder
import com.avtelma.tagaevplanner.R
import com.avtelma.tagaevplanner.models.SphereType
import com.avtelma.tagaevplanner.models.Task
import com.avtelma.tagaevplanner.navigation.Screen
import com.avtelma.tagaevplanner.ui.MainViewModel
import com.avtelma.tagaevplanner.ui.theme.colorBottomCard
import com.avtelma.tagaevplanner.ui.theme.colorFontBurrito
import com.avtelma.tagaevplanner.ui.theme.colorTopCard

@Composable
fun MainSphereScreen(mainViewModel: MainViewModel, navCtrl: NavHostController) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.Black)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        ) {

        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Green)) {
                                append("Tagaev")
                            }
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append("Planner")
                            }
                        },
                        color = Color.Black,
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .height(IntrinsicSize.Min)
                            .padding(10.dp),
                        fontSize = 30.sp,fontFamily = FontFamily(
                            ResourcesCompat.getFont(
                                LocalContext.current, R.font.rubik_regular)!!)
                    )
                    Button(
                        onClick = {
                            navCtrl.navigate(Screen.AddSphereScreen.route)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                        // Uses ButtonDefaults.ContentPadding by default
                        contentPadding = PaddingValues(
                            start = 20.dp,
                            top = 12.dp,
                            end = 20.dp,
                            bottom = 12.dp
                        )
                    ) {
                        // Inner content including an icon and a text label
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Add",
                            tint = Color.White,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Add Sphere", color = Color.White)
                    }
                }

            }
            itemsIndexed(
                Holder.initialCurrencyPrices//.filter { it.isFavorite }
            ) { index: Int, item: Task ->
                RowSphere(index, item, navCtrl)
            }
        }
    }
}

@Composable
private fun RowSphere(index: Int, item: Task, navCtrl: NavHostController) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(10.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.clickable {

            when(item.type) {
                SphereType.PROGRESSER -> navCtrl.navigate(Screen.ProgresserScreen.route)
                SphereType.COPYPASTER -> navCtrl.navigate(Screen.CopyPasterScreen.route)
            }

        }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
                    .background(colorTopCard)
            ) {
                Text(
                    modifier = Modifier.padding(start = 25.dp, top = 5.dp),
                    text = "${item.taskDescription}  ${item.idSphere}",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            ///////
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(colorBottomCard)

            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 25.dp, bottom = 5.dp)
                        .align(Alignment.BottomEnd),
                    text = "${item.taskDescription}  ${item.idSphere}",
                    color = colorFontBurrito,
                    fontSize = 16.sp
                )
            }
        }


    }
}

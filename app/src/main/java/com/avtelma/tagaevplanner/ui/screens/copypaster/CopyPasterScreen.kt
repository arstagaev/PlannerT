package com.avtelma.tagaevplanner.ui.screens.copypaster

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.avtelma.tagaevplanner.Holder
import com.avtelma.tagaevplanner.models.SphereType
import com.avtelma.tagaevplanner.models.Task
import com.avtelma.tagaevplanner.navigation.Screen
import com.avtelma.tagaevplanner.ui.MainViewModel
import com.avtelma.tagaevplanner.ui.theme.colorBottomCard
import com.avtelma.tagaevplanner.ui.theme.colorFontBurrito
import com.avtelma.tagaevplanner.ui.theme.colorTopCard

@Composable
fun CopyPasterScreen(mainViewModel: MainViewModel, navCtrl: NavHostController) {
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
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
                    .background(colorTopCard)
                    .clickable {
                        navCtrl.navigate(Screen.ProgresserScreen.route)
                    }
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
                    .clickable {

//                        when(item.idSphere) {
//                            SphereType.PROGRESSER.id -> navCtrl.navigate(Screen.ProgresserScreen.route)
//                            SphereType.COPYPASTER.id -> navCtrl.navigate(Screen.Co.route)
//                        }

                    }
            ) {
                Text(
                    modifier = Modifier.padding(end = 25.dp, bottom = 5.dp).align(Alignment.BottomEnd),
                    text = "${item.taskDescription}  ${item.idSphere}",
                    color = colorFontBurrito,
                    fontSize = 16.sp
                )
            }
        }


    }
}
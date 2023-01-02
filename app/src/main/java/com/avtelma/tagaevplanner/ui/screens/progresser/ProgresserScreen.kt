package com.avtelma.tagaevplanner.ui.screens.progresser

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavHostController
import com.avtelma.tagaevplanner.Holder.initialCurrencyPrices
import com.avtelma.tagaevplanner.InfiniteProgressView
import com.avtelma.tagaevplanner.R
import com.avtelma.tagaevplanner.models.SphereType
import com.avtelma.tagaevplanner.models.Task
import com.avtelma.tagaevplanner.navigation.Screen
import com.avtelma.tagaevplanner.ui.MainViewModel
import com.avtelma.tagaevplanner.ui.screens.addtask.AddTaskProgressor
import com.avtelma.tagaevplanner.ui.theme.colorButtonAdd
import com.avtelma.tagaevplanner.visibleListOfTasks

@Composable
fun ProgresserScreen(mainViewModel: MainViewModel, navController: NavHostController) {

    val listState = rememberLazyListState()

    val wellNewUpd = (mainViewModel.currentTasks).collectAsState()

    // first home screen
    Box(modifier = Modifier.fillMaxSize()) {
//                            Row(Modifier.fillMaxWidth().height(80.dp)) {
//
//                            }
        LazyColumn (
            modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxHeight()
                .background(Color.White), state = listState
            //.height(300.dp)
        ) {

            //var counter = 0
            item {
                Text(
                    text = "Progresser",
                    color = Color.Black,
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .height(IntrinsicSize.Min)
                        .padding(10.dp),
                    fontSize = 30.sp,fontFamily = FontFamily(
                        ResourcesCompat.getFont(
                            LocalContext.current, R.font.rubik_regular)!!)
                )
            }
            itemsIndexed(
                wellNewUpd.value.filter { it.type == SphereType.PROGRESSER }//.sortedByDescending { it.taskDescription }//.sorted()
            ) { index : Int, task : Task ->
                ProgressRow(task,index, navController)
//                if (task.idSphere == SphereType.PROGRESSER.id) {
//
//                }

            }
        }
        FloatingActionButton(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(10.dp),

            onClick = { visibleListOfTasks.value = false },
            elevation = FloatingActionButtonDefaults.elevation(30.dp),
            backgroundColor = Color.Magenta,
            contentColor = Color.White
        ){
            Icon(
                Icons.Filled.Add,"", modifier = Modifier,
                contentColorFor(backgroundColor = colorButtonAdd)
            )
        }
    }
}

@Composable
fun ProgressRow(task: Task, index: Int, navController: NavHostController) {
    var tasker by remember {
        mutableStateOf(task)
    }
    // Get local density from composable
    val localDensity = LocalDensity.current
    // Create element height in dp state
    var widthC by remember {
        mutableStateOf(0.dp)
    }
    var pointerBarrierMove by remember {
        mutableStateOf(0f)
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .height(200.dp)
            .fillMaxWidth()
            //.verticalScroll(rememberScrollState(), enabled = true)
            //.background(Color.Cyan)
            .clickable {
                //onCardActive2(index,task.progress,"NEW${(0..100).random()}")
//                    initialCurrencyPrices[0].progress = 200f
//                    initialCurrencyPrices[0].taskDescription = "200f"
            }
            .shadow(
                elevation = 20.dp, shape = RoundedCornerShape(18.dp)
            ),
        //backgroundColor = Color.Green
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
            //.verticalScroll(rememberScrollState())
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.TopCenter)
                    .onGloballyPositioned { coordinates ->
                        // Set column height using the LayoutCoordinates
                        widthC = with(localDensity) { coordinates.size.height.toDp() }
                    }
                    .pointerInput(Unit) {
                        forEachGesture {

                            awaitPointerEventScope {

                                awaitFirstDown()
                                do {
                                    //This PointerEvent contains details including
                                    // event, id, position and more
                                    val event: PointerEvent = awaitPointerEvent()
                                    Log.w("fff", "fff ${event.buttons}")
                                    // Consuming event prevents other gestures or scroll to intercept
                                    event.changes.forEach { pointerInputChange: PointerInputChange ->

//                                        initialCurrencyPrices[index].progress =
//                                            pointerInputChange.position.x
                                        //onCardActive2(index, pointerInputChange.position.x)
                                        pointerBarrierMove = pointerInputChange.position.x
                                        initialCurrencyPrices[index].progress =
                                            widthC.value / pointerInputChange.position.x
                                        Log.w(
                                            "fff",
                                            "fffxxx ${pointerInputChange.position.x} ${pointerInputChange.position.y}  >> ${initialCurrencyPrices.joinToString()}"
                                        )
                                        pointerInputChange.consumePositionChange()
                                    }
                                } while (event.changes.any { it.pressed })

                                // ACTION_UP is here
                            }
                        }
                    }
            ) {

                InfiniteProgressView(
                    modifier = Modifier.fillMaxSize(), heightINP = 100.dp, fl = pointerBarrierMove//tasker.progress
                )

            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    //.verticalScroll(rememberScrollState(), enabled = false)
                    .align(Alignment.BottomCenter)
                    .background(Color.Blue)
                    .clickable {
                        navController.navigate(Screen.AddTaskScreen.route)
                    }
            ) {
                Text(
                    text = tasker.taskDescription//if (ttt.value.isNotEmpty()) ttt.value[index].taskDescription else task.taskDescription
                    ,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(10.dp)
                        .align(Alignment.Center),
                    fontSize = 40.sp,fontFamily = FontFamily(
                        ResourcesCompat.getFont(
                            LocalContext.current, R.font.rubik_regular)!!)
                )
            }
        }
    }
}

// onclicker
//private fun onCardActive2(index: Int,progress: Float,description :String? = null) {
//    println("UPD")
//    var UPDCur = Task(progress,"false", idSphere = "")
//    if (description != null) {
//        UPDCur = Task(progress = progress, taskDescription = description, idSphere = "")
//    }else {
//        UPDCur = Task(progress = progress, taskDescription = initialCurrencyPrices[index].taskDescription, idSphere = "")
//    }
//
//    val mutableCurrencyPrices = Holder._currentTasks.value.toMutableList()
//    mutableCurrencyPrices[index] = UPDCur
//    initialCurrencyPrices = mutableCurrencyPrices as ArrayList<Task> //as MutableList<Task>
//}

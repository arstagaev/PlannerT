package com.avtelma.tagaevplanner

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.avtelma.tagaevplanner.ui.addTaskProgressor
import com.avtelma.tagaevplanner.ui.theme.TagaevPlannerTheme

var tasks = mutableStateListOf<Task>(
    Task(mutableStateOf<Float>(12f),   ""),
    Task(mutableStateOf<Float>(1f),    ""),
    Task(mutableStateOf<Float>(100f),  ""),
    Task(mutableStateOf<Float>(92f),   ""),
    Task(mutableStateOf<Float>(82f),   ""),
    Task(mutableStateOf<Float>(32f),   "")
)

var tasks2 = mutableStateListOf<Float>(
    ((12f)),
    ((1f) ),
    ((100f)),
    ((92f)),
    ((82f)),
    ((32f))
)

var pro = arrayListOf<Float>(1f,1f,1f,1f,1f,1f,11f,23f)
var visibleListOfTasks = mutableStateOf<Boolean>(true)

data class Task(var progress : MutableState<Float>, var taskDescription : String)

@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TagaevPlannerTheme {
//                var adsX = remember {
//                    mutableStateOf(tasks)
//                }
                val listState = rememberLazyListState()


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AnimatedVisibility(visible = visibleListOfTasks.value) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            LazyColumn (
                                modifier = Modifier
                                    .padding(top = 0.dp)
                                    .fillMaxHeight()
                                    .background(Color.White), state = listState
                                //.height(300.dp)
                            ) {

                                //var counter = 0

                                itemsIndexed(tasks2.toList().sorted()
                                ) { index : Int, task : Float ->
                                    proster(task,index)
                                }
                            }
                            FloatingActionButton(modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp),
//                                icon = { Icon(Icons.Filled.Add,"") },
//                                text = { //Text("FloatingActionButton")
//                                       },
                                onClick = { visibleListOfTasks.value = false },
                                elevation = FloatingActionButtonDefaults.elevation(30.dp),
                                backgroundColor = Color.Blue,
                                contentColor = Color.White
                            ){
                                Icon(Icons.Filled.Add,"", modifier = Modifier)
                            }
                        }

                    }
                    AnimatedVisibility(visible = !visibleListOfTasks.value) {
                        addTaskProgressor()
                    }



                }
            }
        }
    }


    @Composable
    private fun proster(message: Float, index: Int) {
        var vert = rememberScrollState(0)
        var horz = rememberScrollState(0)

        var adsX = remember {
           message
        }

        var lastY = 0f
        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .height(210.dp)
                .fillMaxWidth()
                //.verticalScroll(rememberScrollState(), enabled = true)
                //.background(Color.Cyan)
//            .clickable {
//
//            }
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
//                        .horizontalScroll(horz)
                        //.verticalScroll(vert,enabled = false)
//                        .nestedScroll(
//                            object : NestedScrollConnection {
//                                override fun onPostScroll(
//                                    consumed: Offset,
//                                    available: Offset,
//                                    source: NestedScrollSource
//                                ): Offset {
//                                    Log.w("wwww","wwcc ${horz.value}")
//                                    return super.onPostScroll(consumed, available, source)
//                                }
//
//                                // Implement callbacks here
//                            }
//                        )
                        .align(Alignment.TopCenter)
                        .pointerInput(Unit) {
                            forEachGesture {

                                awaitPointerEventScope {

                                    awaitFirstDown()
                                    // ACTION_DOWN here

                                    do {

                                        //This PointerEvent contains details including
                                        // event, id, position and more
                                        val event: PointerEvent = awaitPointerEvent()
                                        // ACTION_MOVE loop
                                        Log.w("fff", "fff ${event.buttons}")
                                        // Consuming event prevents other gestures or scroll to intercept
                                        event.changes.forEach { pointerInputChange: PointerInputChange ->
                                            Log.w(
                                                "fff",
                                                "fffxxx ${pointerInputChange.position.x} ${pointerInputChange.position.y}"
                                            )
                                            tasks2[index] = pointerInputChange.position.x

                                            pointerInputChange.consumePositionChange()
                                        }
                                    } while (event.changes.any { it.pressed })

                                    // ACTION_UP is here
                                }
                            }
                        }
//                        .pointerInteropFilter {
//                            when (it.action) {
//                                MotionEvent.ACTION_DOWN -> {
//                                    lastY = it.y
//                                }
//                                MotionEvent.ACTION_UP -> {}
//                                MotionEvent.ACTION_MOVE -> {
//                                    //it.setLocation(message,100f)
//
//
//                                    asdf[index] = it.x
//                                    it.setLocation(it.x,lastY)
//                                    Log.w("www", "www> ${it.x}  ${it.y}<")
//                                }
//                                else -> false
//                            }
//                            true
//                        }
                ) {

                    InfiniteProgressView(modifier = Modifier.fillMaxSize(), heightINP = 100.dp, fl = tasks2[index])

                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        //.verticalScroll(rememberScrollState(), enabled = false)
                        .align(Alignment.BottomCenter)
                        .background(Color.Blue)
                        ) {
                    Text(
                        text = "New task #${index}",
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
}




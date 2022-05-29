package com.avtelma.tagaevplanner

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
import com.avtelma.tagaevplanner.Holder.SPECIAL_FLOW
import com.avtelma.tagaevplanner.Holder._currentTasks
import com.avtelma.tagaevplanner.Holder.currentTasks
import com.avtelma.tagaevplanner.Holder.initialCurrencyPrices
import com.avtelma.tagaevplanner.ui.addTaskProgressor
import com.avtelma.tagaevplanner.ui.theme.TagaevPlannerTheme
import com.avtelma.tagaevplanner.ui.theme.colorButtonAdd
import kotlinx.coroutines.flow.*

var cards = MutableStateFlow(
    mutableListOf<Task>(
        Task(500f,   ""),
        Task(1f,    "")
    )
)

var tasks = arrayListOf<Task>(
    Task(500f,   ""),
    Task(1f,    ""),
    Task(100f,  ""),
    Task(92f,   ""),
    Task(82f,   ""),
    Task(32f,   "")
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

data class Task(var progress : Float, var taskDescription : String)

@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Generator().engine()

        setContent {
            TagaevPlannerTheme {
//                var adsX = remember {
//                    mutableStateOf(tasks)
//                }
//                val flow by remember {
//
//                }

//                val cardx by remember {
//
//                    val data = mutableListOf<Task>()
//
//                    derivedStateOf {
//                        flow.run { data::add }
//
//                        flow?.run(data::add)
//                        data
//                    }
//                    //cards
//                }
                //Holder.SPECIAL_FLOW.collectAsState(initial = arrayListOf<Task>())
                var flow = remember {
                    tasks.asFlow()
                    // .collectAsState(initial = arrayListOf<Task>())
                }
                //val podcasts = SPECIAL_FLOW.collectAsState(arrayListOf())

                val listState = rememberLazyListState()

                var asd = remember {
                    tasks
                }
                val wellNewUpd = currentTasks.collectAsState()


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    AnimatedVisibility(visible = visibleListOfTasks.value) {
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
                                        text = "TagaevPlanner",
                                        color = Color.Black,
                                        modifier = Modifier
                                            .width(IntrinsicSize.Min)
                                            .height(IntrinsicSize.Min)
                                            .padding(10.dp),
                                        fontSize = 30.sp,fontFamily = FontFamily(ResourcesCompat.getFont(LocalContext.current, R.font.rubik_regular)!!)
                                    )
                                }
                                itemsIndexed(
                                    wellNewUpd.value//.sortedByDescending { it.taskDescription }//.sorted()
                                ) { index : Int, task : Task ->
                                    proster(task,index)
                                }
                            }
                            FloatingActionButton(modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(10.dp),
//                                icon = { Icon(Icons.Filled.Add,"") },
//                                text = { //Text("FloatingActionButton")
//                                       },
                                onClick = { visibleListOfTasks.value = false },
                                elevation = FloatingActionButtonDefaults.elevation(30.dp),
                                backgroundColor = Color.Blue,
                                contentColor = Color.White
                            ){
                                Icon(Icons.Filled.Add,"", modifier = Modifier, contentColorFor(
                                    backgroundColor = colorButtonAdd
                                ))
                            }
                        }
                    }
                    AnimatedVisibility(visible = !visibleListOfTasks.value) {
                        //second screen creator of task
                        addTaskProgressor()
                    }
                }
            }
        }
    }


    @Composable
    fun proster(task: Task, index: Int) {
        var vert = rememberScrollState(0)
        var horz = rememberScrollState(0)
        var ttt = SPECIAL_FLOW.collectAsState(initial = arrayListOf())
        var trust = remember {
            mutableStateOf(task)
        }
        var adsX = remember {
           mutableStateOf(task.progress)
        }
        //var cardx = cards.collectAsState()

        var lastY = 0f


        Card(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
                .height(200.dp)
                .fillMaxWidth()
                //.verticalScroll(rememberScrollState(), enabled = true)
                //.background(Color.Cyan)
                .clickable {
                    onCardActive2(index,task.progress,"NEW${(0..100).random()}")
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
                        .pointerInput(Unit) {
                            forEachGesture {

                                awaitPointerEventScope {

                                    awaitFirstDown()
                                    do {
                                        //This PointerEvent contains details including
                                        // event, id, position and more
                                        val event: PointerEvent = awaitPointerEvent()
                                        // ACTION_MOVE loop
                                        Log.w("fff", "fff ${event.buttons}")
                                        // Consuming event prevents other gestures or scroll to intercept
                                        event.changes.forEach { pointerInputChange: PointerInputChange ->

                                            //
//                                            cards.value[index].progress =
//                                                pointerInputChange.position.x
                                            //tasks[index].progress = pointerInputChange.position.x
                                            initialCurrencyPrices[index].progress = pointerInputChange.position.x
                                            onCardActive2(index,pointerInputChange.position.x)
                                            //adsX.value = pointerInputChange.position.x

                                            //message.value = pointerInputChange.position.x

                                            //tasks[index].progress = pointerInputChange.position.x
                                            //adsX.value.progress = tasks[index].progress
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
                        modifier = Modifier.fillMaxSize(), heightINP = 100.dp, fl =task.progress //adsX.value //tasks[index].progress
                    )

                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        //.verticalScroll(rememberScrollState(), enabled = false)
                        .align(Alignment.BottomCenter)
                        .background(Color.Blue)
                        ) {
                    Text(
                        text = task.taskDescription//if (ttt.value.isNotEmpty()) ttt.value[index].taskDescription else task.taskDescription
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
    private fun onCardActive2(index: Int,progress: Float,description :String? = null) {
        println("UPD")
        var UPDCur = Task(progress,"false")
        if (description != null) {
            UPDCur = Task(progress = progress, taskDescription = description)
        }else {
            UPDCur = Task(progress = progress, taskDescription = initialCurrencyPrices[index].taskDescription)
        }

        val mutableCurrencyPrices = _currentTasks.value.toMutableList()
        mutableCurrencyPrices[index] = UPDCur
        initialCurrencyPrices = mutableCurrencyPrices as ArrayList<Task> //as MutableList<Task>
    }
}




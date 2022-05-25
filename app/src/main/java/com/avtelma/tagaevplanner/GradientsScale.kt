package com.avtelma.tagaevplanner

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
//import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun InfiniteProgressView(
    modifier: Modifier = Modifier,
    //progressColor: Color = blue,
    //progressBackgroundColor: Color = light_blue,
    strokeWidth: Dp = 10.dp,
    strokeBackgroundWidth: Dp = 10.dp,
    //progressDirection: AnimationDirection = AnimationDirection.RIGHT,
    roundedBorder: Boolean = false,
    durationInMilliSecond: Int = 800,
    radius: Dp = 80.dp,
    heightINP: Dp,
    fl: Float
) {


    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = if(roundedBorder) StrokeCap.Round else StrokeCap.Square)
    }

    val strokeBackground = with(LocalDensity.current) {
        Stroke(width = strokeBackgroundWidth.toPx())
    }

    val transition = rememberInfiniteTransition()

//    val animatedRestart by transition.animateFloat(
//        initialValue = 0f,
//        targetValue = if(progressDirection == AnimationDirection.RIGHT) 360f else -360f,
//        animationSpec = infiniteRepeatable(tween(durationInMilliSecond, easing = LinearEasing))
//    )
    val gradient45 = Brush.linearGradient(
        colors = listOf(Color.Yellow, Color.Red),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )
    Canvas(
        Modifier.fillMaxWidth().height(heightINP).background(gradient45)
    ) {
        var colors = listOf<Color>( Color(0xFF2C972C),Color(0xFF14BB14), Color(0xFF00FF00))

//        val gradient = LinearGradient(
//            ,
//            tileMode = TileMode.Clamp
//        )

        val higherStrokeWidth =
            if (stroke.width > strokeBackground.width) stroke.width else strokeBackground.width
        val radius = (size.minDimension - higherStrokeWidth) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
            halfSize.width - radius,
            halfSize.height - radius
        )
        val size = Size(radius * 2, radius * 2)

        drawRect(brush = Brush.linearGradient(
            colors = colors,
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        ), Offset.Zero, Size(width = fl,275f))
    }

}
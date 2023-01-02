package com.avtelma.tagaevplanner

import com.avtelma.tagaevplanner.models.SphereType
import com.avtelma.tagaevplanner.models.Task
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object Holder {

    var initialCurrencyPrices = arrayListOf(
        Task(1f,"Pixdec1",SphereType.PROGRESSER,"My push ups"),
        Task(1f,"Pixdec2",SphereType.PROGRESSER,"My push ups"),
        Task(1f,"Pixdec3",SphereType.PROGRESSER,"My push ups"),
        Task(1f,"Pixdec4",SphereType.PROGRESSER,"My push ups"),
        Task(1f,"Pixdec5",SphereType.COPYPASTER,"My push ups")
    )
}
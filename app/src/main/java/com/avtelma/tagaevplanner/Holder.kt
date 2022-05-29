package com.avtelma.tagaevplanner

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object Holder {

    var SPECIAL_FLOW = MutableSharedFlow<ArrayList<Task>>()

    val _currentTasks = MutableStateFlow(listOf<Task>())
    val currentTasks: StateFlow<List<Task>> get() =  _currentTasks //get() = _currencyPrices

    var initialCurrencyPrices = arrayListOf(
        Task(1f,"Pixdec")
    )
}
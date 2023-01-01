package com.avtelma.tagaevplanner.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avtelma.tagaevplanner.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //private val repo: CurrencyRepository
) : ViewModel() {
    var listOfNotesX = mutableStateListOf<Task>()

    init {

        CoroutineScope(viewModelScope.coroutineContext).launch {
            delay(1000)
        }
    }

    fun fillList() {
        //listOfNotesX = getListChanges()
    }
}

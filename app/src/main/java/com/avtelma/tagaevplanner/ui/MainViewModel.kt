package com.avtelma.tagaevplanner.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avtelma.tagaevplanner.models.Sphere
import com.avtelma.tagaevplanner.models.Task
import com.avtelma.tagaevplanner.storage.StorageSphereRouter
import com.avtelma.tagaevplanner.storage.StorageTaskRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //private val repo: CurrencyRepository
) : ViewModel() {
    var listOfNotesX = mutableStateListOf<Task>()

    val _currentTasks = MutableStateFlow(listOf<Task>())
    val currentTasks: StateFlow<List<Task>> get() =  _currentTasks //get() = _currencyPrices

    val _currentSpheres = MutableStateFlow(listOf<Sphere>())
    val currentSpheres: StateFlow<List<Sphere>> get() =  _currentSpheres //get() = _currencyPrices


    init {
        fillList()

    }

    fun fillList() {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            StorageSphereRouter.getListChanges()?.toList()?.let {
                _currentSpheres.emit(it)
            }
            StorageTaskRouter.getListChanges()?.toList()?.let {
                _currentTasks.emit(it)
            }
            delay(1000)

        }
    }

    fun addSphere(sphere: Sphere) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            //StorageSphereRouter.saveChanges(arrayList)
            StorageSphereRouter.onAdd(sphere)
            StorageSphereRouter.getListChanges()?.toList()?.let {
                _currentSpheres.emit(it)
            }
        }
    }
}

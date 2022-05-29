package com.avtelma.tagaevplanner

import com.avtelma.tagaevplanner.Holder.SPECIAL_FLOW
import com.avtelma.tagaevplanner.Holder._currentTasks
import com.avtelma.tagaevplanner.Holder.initialCurrencyPrices
import kotlinx.coroutines.*

class Generator {

    fun engine() {

        GlobalScope.launch {
            while (true) {
                delay(1)
                initialCurrencyPrices.forEachIndexed { index, box ->

                }
//                run loop@{
//                    _currencyPrices.value.forEachIndexed { index, currency ->
//                        //if (index == 5) return@loop
//                        initialCurrencyPrices += "WELLL${(0..100).random()}"
//                    }
//                }
                _currentTasks.emit(initialCurrencyPrices)


                delay(10)
            }
        }

    }
}
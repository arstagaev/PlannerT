package com.avtelma.tagaevplanner.storage

import android.content.Context
import android.content.SharedPreferences


object PreferenceStorage {

    private var NAME = "TagaevPlanner"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    //private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }



    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    var allNotes: String
        get() = preferences.getString("allNotes", "")!!
        set(value) = preferences.edit {
            it.putString("allNotes", value)
        }

    var allSpheres: String
        get() = preferences.getString("allSpheres", "")!!
        set(value) = preferences.edit {
            it.putString("allSpheres", value)
        }


}
package com.avtelma.tagaevplanner.storage

import android.util.Log
import com.avtelma.tagaevplanner.models.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object StorageTaskRouter: Saver<Task> {
    init {
        println("init StorageRouter: ")
        //println("<>>> ${listNotes.joinToString()}              <")
        println("<>>> ${PreferenceStorage.allNotes.toString()} <")
    }

    override fun onAdd(new: Task): Boolean {
        //listNotes.add(newNote)
        return true
    }

    override fun onDelete(del: Task) {

    }

    override fun saveChanges(list: ArrayList<Task>) {

        println("saver is ${list.joinToString()}")
        PreferenceStorage.allNotes = Gson().toJson(list)
        println("saver is< ${PreferenceStorage.allNotes}")
    }

    override fun getListChanges(): ArrayList<Task>? {
        var logs = arrayListOf<Task>()
        val gson = Gson()
        val SYNC_TYPE = object : TypeToken<ArrayList<Task>>() {}.type

        try {
            logs = gson.fromJson(PreferenceStorage.allNotes, SYNC_TYPE)
            println("getSyncChanges() : ${logs.size}")

        } catch (e: Exception) {
            Log.e("CRUD ERROR",">> ${e.message}")
        }


        if (logs.isEmpty()) {
            return null
        }

        return logs
    }

    override fun onUpdate(new: Task) {
    }
}
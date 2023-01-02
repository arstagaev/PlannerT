package com.avtelma.tagaevplanner.storage

import android.util.Log
import com.avtelma.tagaevplanner.models.Sphere
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object StorageSphereRouter: Saver<Sphere> {

    init {
        println("init StorageSphereRouter: ")
        //println("<>>> ${listNotes.joinToString()}              <")
        println("<>>> ${PreferenceStorage.allNotes.toString()} <")
    }

    override fun onAdd(new: Sphere): Boolean {
        val newArr = getListChanges()
        newArr?.add(new)

        return if (newArr != null) {
            saveChanges(newArr)
            true
        }else {
            false
        }

    }

    override fun onDelete(del: Sphere) {

    }

    override fun saveChanges(list: ArrayList<Sphere>) {

        println("saver is ${list.joinToString()}")
        PreferenceStorage.allNotes = Gson().toJson(list)
        println("saver is< ${PreferenceStorage.allNotes}")
    }

    override fun getListChanges(): ArrayList<Sphere>? {
        var logs = arrayListOf<Sphere>()
        val gson = Gson()
        val SYNC_TYPE = object : TypeToken<ArrayList<Sphere>>() {}.type

        try {
            logs = gson.fromJson(PreferenceStorage.allSpheres, SYNC_TYPE)
            println("getSyncChanges() : ${logs.size}")

        }catch (e: Exception) {
            Log.e("CRUD ERROR",">> ${e.message}")
        }


        if (logs.isEmpty()) {
            return null
        }

        return logs
    }

    override fun onUpdate(new: Sphere) {
    }
}
package com.avtelma.tagaevplanner.storage


interface Saver<E> {

    fun onAdd(new: E) : Boolean
    fun onUpdate(new: E)
    fun onDelete(del: E)

    fun saveChanges(list: ArrayList<E>)
    fun getListChanges(): List<E>?
}

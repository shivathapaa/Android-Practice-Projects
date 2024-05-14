package com.stapplications.notes.presentation.screens.todo.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromStringPairList(value: String): List<Pair<String, Boolean>> {
        val pairList = mutableListOf<Pair<String, Boolean>>()
        val pairs = value.split("|")
        for (pairStr in pairs) {
            val components = pairStr.split(";")
            val todoListString = components[0].trim()
            val booleanValue = components[1].trim().toBoolean()
            pairList.add(Pair(todoListString, booleanValue))
        }
        return pairList
    }

    @TypeConverter
    fun toStringPairList(pairList: List<Pair<String, Boolean>>): String {
        return pairList.joinToString("|") { "${it.first};${it.second}" }
    }
}


/*
class Converters {
    @TypeConverter
    fun fromStringList(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }

    @TypeConverter
    fun toStringList(list: List<String>): String {
        return list.joinToString(",")
    }
}*/

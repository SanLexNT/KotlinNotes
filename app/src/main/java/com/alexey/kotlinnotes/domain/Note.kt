package com.alexey.kotlinnotes.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Note
    (@PrimaryKey(autoGenerate = true)
     val id: Int = 0,
     @ColumnInfo
     var title: String,
     @ColumnInfo
     var description: String) : Serializable
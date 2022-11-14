package com.alexey.kotlinnotes.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexey.kotlinnotes.domain.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase() {

    abstract fun getDao() : NoteDao

    companion object{

        private var instance: NoteDB?= null

        @Synchronized
        fun getInstance(context: Context) : NoteDB{

            if(instance == null){

                instance = Room.databaseBuilder(context, NoteDB::class.java, "db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as NoteDB
        }
    }
}
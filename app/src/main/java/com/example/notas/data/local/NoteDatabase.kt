package com.example.notas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notas.data.local.entity.NoteEntity

@Database(
    version = 4,
    entities = [NoteEntity::class]
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val dao: NoteDao

    companion object{
        const val DB_NAME = "notes_db"
    }

}
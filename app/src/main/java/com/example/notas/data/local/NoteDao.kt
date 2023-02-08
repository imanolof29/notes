package com.example.notas.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.notas.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity Where completed = 0 AND dueDate > :today")
    fun getPendingNotes(today: Long): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity Where completed = 0 AND dueDate < :today")
    fun getExpiredNotes(today: Long): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity Where completed = 1")
    fun getCompletedNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity WHERE id = :id")
    fun getNoteById(id: Int): Flow<NoteEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update(entity = NoteEntity::class)
    suspend fun updateNote(note: NoteEntity)

}
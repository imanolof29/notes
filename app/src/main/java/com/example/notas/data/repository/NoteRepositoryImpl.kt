package com.example.notas.data.repository

import com.example.notas.data.local.NoteDao
import com.example.notas.data.mapper.toDomain
import com.example.notas.data.mapper.toEntity
import com.example.notas.domain.models.Note
import com.example.notas.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZoneId
import java.time.LocalDateTime
import java.time.Instant
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
): NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override fun getPendingNotes(): Flow<List<Note>> {
        return dao.getPendingNotes(
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        ).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override fun getCompletedNotes(): Flow<List<Note>> {
        return dao.getCompletedNotes().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override fun getExpiredNotes(): Flow<List<Note>> {
        return dao.getExpiredNotes(
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        ).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override fun getNoteById(id: Int): Flow<Note> {
        return dao.getNoteById(id).map {
            it.toDomain()
        }
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note.toEntity())
    }

}
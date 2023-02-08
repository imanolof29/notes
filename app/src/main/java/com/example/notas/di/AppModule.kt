package com.example.notas.di

import android.app.Application
import androidx.room.Room
import com.example.notas.data.local.NoteDao
import com.example.notas.data.local.NoteDatabase
import com.example.notas.data.repository.NoteRepositoryImpl
import com.example.notas.domain.repository.NoteRepository
import com.example.notas.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDao(database: NoteDatabase):NoteDao = database.dao

    @Singleton
    @Provides
    fun provideDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRepository(
        dao: NoteDao
    ): NoteRepository {
        return NoteRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun provideGetNotesUseCase(
        noteRepository: NoteRepositoryImpl
    ) = GetNotes(noteRepository)

    @Singleton
    @Provides
    fun provideGetPendingNotesUseCase(
        noteRepository: NoteRepositoryImpl
    ) = GetPendingNotes(noteRepository)

    @Singleton
    @Provides
    fun provideGetCompletedNotesUseCase(
        noteRepository: NoteRepositoryImpl
    ) = GetCompletedNotes(noteRepository)

    @Singleton
    @Provides
    fun provideExpiredNotesUseCase(
        noteRepository: NoteRepositoryImpl
    ) = GetExpiredNotes(noteRepository)

    @Singleton
    @Provides
    fun provideInsertNoteUseCase(
        noteRepository: NoteRepositoryImpl
    ) = InsertNote(noteRepository)

    @Singleton
    @Provides
    fun provideGetNoteByIdUseCase(
        noteRepository: NoteRepositoryImpl
    ) = GetNoteById(noteRepository)

    @Singleton
    @Provides
    fun provideDeleteNote(
        noteRepository: NoteRepositoryImpl
    ) = DeleteNote(noteRepository)

    @Singleton
    @Provides
    fun provideUpdateNote(
        noteRepository: NoteRepositoryImpl
    ) = UpdateNote(noteRepository)

}
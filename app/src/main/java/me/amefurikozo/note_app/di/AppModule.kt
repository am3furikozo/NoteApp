/*
 * **********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of NoteApp project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * **********************************************************************************************
 */

package me.amefurikozo.note_app.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.amefurikozo.note_app.feature.note.data.repository.NoteRepositoryImpl
import me.amefurikozo.note_app.feature.note.data.source.NoteDatabase
import me.amefurikozo.note_app.feature.note.domain.repository.NoteRepository
import me.amefurikozo.note_app.feature.note.domain.service.NoteService
import me.amefurikozo.note_app.feature.note.domain.service.NoteServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
  fun provideNoteDatabase(app: Application): NoteDatabase {
    return Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME).build()
  }

  @Provides
  @Singleton
  fun provideNoteRepository(db: NoteDatabase): NoteRepository {
    return NoteRepositoryImpl(db.noteDao)
  }

  @Provides
  @Singleton
  fun provideNoteService(app: Application, repo: NoteRepository): NoteService {
    return NoteServiceImpl(app, repo)
  }
}
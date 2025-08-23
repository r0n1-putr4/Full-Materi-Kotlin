package roni.putra.fullmateri.sqlite.room

import androidx.room.Insert
import androidx.room.Query

interface NoteDao {

    @Query("SELECT * from note")
    suspend fun getNotes(): List<Note>

    @Query("SELECT * from note WHERE id =:note_id")
    suspend fun getNote(note_id: Int): List<Note>

    @Insert()
    suspend fun insert(note: Note)

    @Query("DELETE FROM note")
    suspend fun deleteNote()

}
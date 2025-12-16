package roni.putra.fullmateri.sqlite.config

import android.content.ContentValues
import android.content.Context
import roni.putra.fullmateri.sqlite.model.Note

class NoteDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertNote(note: Note): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues()

        values.put("judul", note.judul)
        values.put("isi", note.isi)

        val result = db.insert("notes", null, values)
        return result != -1L
    }

    fun getAllNote(): ArrayList<Note> {
        val notes = ArrayList<Note>()
        val db = dbHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM note", null)
        if (cursor.moveToFirst()) {
            do {
                notes.add(
                    Note(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return notes
    }

    fun deleteNote(id: Int): Boolean {
        val db = dbHelper.readableDatabase
        val result = db.delete("notes", "id=?", arrayOf(id.toString()))
        return result > 0
    }

    fun updateNote(id: Int,note: Note): Boolean {
        val db = dbHelper.readableDatabase
        val values = ContentValues()
        values.put("judul", note.judul)
        values.put("isi", note.isi)
        val result = db.update("notes", values, "id=?", arrayOf(id.toString()))
        return result > 0
    }

}
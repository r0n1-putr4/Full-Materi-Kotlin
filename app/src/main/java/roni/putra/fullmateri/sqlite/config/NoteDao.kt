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

    fun getAllNotes(): List<Note> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM notes", null)

        return mutableListOf<Note>().apply {
            if (cursor.moveToFirst()) {
                do {
                    add(
                        Note(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2)
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }


}
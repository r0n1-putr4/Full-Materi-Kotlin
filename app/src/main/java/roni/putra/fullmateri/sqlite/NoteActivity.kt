package roni.putra.fullmateri.sqlite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import roni.putra.fullmateri.R
import roni.putra.fullmateri.sqlite.adapter.NoteAdapter
import roni.putra.fullmateri.sqlite.config.NoteDao
import roni.putra.fullmateri.sqlite.model.Note

class NoteActivity : AppCompatActivity() {
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var rvNote: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var notes: MutableList<Note>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fabAdd = findViewById(R.id.fabAdd)
        rvNote = findViewById(R.id.rvNote)
    }

    override fun onStart() {
        super.onStart()
        fabAdd.setOnClickListener {
            startActivity(Intent(this@NoteActivity, AddNoteActivity::class.java))
        }
        getData()
    }

    private fun getData() {

        val db = NoteDao(this)
        notes = db.getAllNotes().toMutableList()
        noteAdapter = NoteAdapter(notes, object : NoteAdapter.OnAdapterListener {
            override fun onClick(data: Note) {


            }

            override fun onLongClick(data: Note, position: Int) {
                val success = db.deleteNote(data.id)
                if (success) {
                    notes.removeAt(position)
                    noteAdapter.notifyItemRemoved(position)
                    Toast.makeText(this@NoteActivity, "Berhasil dihapus", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@NoteActivity, "Gagal dihapus", Toast.LENGTH_SHORT).show()
                }
            }
        })
        rvNote.adapter = noteAdapter
        Log.d("Notes ", notes.toString())

    }
}
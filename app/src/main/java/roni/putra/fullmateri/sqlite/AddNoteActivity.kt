package roni.putra.fullmateri.sqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import roni.putra.fullmateri.R
import roni.putra.fullmateri.sqlite.config.NoteDao
import roni.putra.fullmateri.sqlite.model.Note

class AddNoteActivity : AppCompatActivity() {
    private lateinit var btnSimpan: Button
    private lateinit var etTitle: EditText
    private lateinit var etNote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etTitle = findViewById(R.id.etTitle)
        etNote = findViewById(R.id.etNote)
        btnSimpan = findViewById(R.id.btnSimpan)
    }

    override fun onStart() {
        super.onStart()
        simpanData()
    }

    private fun simpanData() {
        btnSimpan.setOnClickListener {
            val repo = NoteDao(this)
            val note = Note(
                judul = etTitle.text.toString(),
                isi = etNote.text.toString()
            )
            val success = repo.insertNote(note)
            if (success) {
                startActivity(Intent(this, NoteActivity::class.java))
            } else {
                Toast.makeText(this, "Gagal menyimpan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
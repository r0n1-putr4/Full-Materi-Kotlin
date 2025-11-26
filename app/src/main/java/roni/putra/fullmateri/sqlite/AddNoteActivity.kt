package roni.putra.fullmateri.sqlite

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import roni.putra.fullmateri.R
import roni.putra.fullmateri.sqlite.Note
import roni.putra.fullmateri.sqlite.room.NoteDB

class AddNoteActivity : AppCompatActivity() {
    private lateinit var btnSimpan: Button
    private lateinit var etTitle: EditText
    private lateinit var etNote: EditText
    val db by lazy { NoteDB(this) }

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
            CoroutineScope(Dispatchers.IO).launch {
                val note = Note(
                    title = "Judul Baru", body = "Isi catatan contoh"
                )
                NoteDB(this@AddNoteActivity).noteDao().insert(note)
            }


        }
    }
}
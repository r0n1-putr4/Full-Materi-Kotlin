package roni.putra.fullmateri.sqlite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import roni.putra.fullmateri.R
import roni.putra.fullmateri.sqlite.config.NoteDao

class NoteActivity : AppCompatActivity() {
    private lateinit var fabAdd: FloatingActionButton
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
    }

    override fun onStart() {
        super.onStart()
        fabAdd.setOnClickListener {
            startActivity(Intent(this@NoteActivity, AddNoteActivity::class.java))
        }
        getData()
    }

    private fun getData(){

        val db = NoteDao(this)
        val notes = db.getAllNotes()
        Log.d("Notes ",notes.toString())

    }
}
package roni.putra.fullmateri.sqlite

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import roni.putra.fullmateri.R

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
    }
}
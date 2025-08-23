package roni.putra.fullmateri.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import roni.putra.fullmateri.R

class HasilActivity : AppCompatActivity() {
    private lateinit var tvHasil: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil)
        tvHasil = findViewById(R.id.tvHasil)

        val bundle = intent.extras
        if (bundle != null) {
            tvHasil.text =  "Nama Mahasiswa = ${bundle.getString("nama")} " +
                    "${bundle.getDouble("nilai",0.0)}"

        }
    }
}
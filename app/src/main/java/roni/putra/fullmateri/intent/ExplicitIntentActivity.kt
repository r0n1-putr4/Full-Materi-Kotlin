package roni.putra.fullmateri.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import roni.putra.fullmateri.R

class ExplicitIntentActivity : AppCompatActivity() {
    private lateinit var btnProses: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_intent)
        btnProses = findViewById(R.id.btnProses)
    }

    override fun onStart() {
        super.onStart()
        proses()
    }

    private fun proses(){
        btnProses.setOnClickListener {
            //cara pertama
            //startActivity(Intent(this,HasilActivity::class.java))

            //cara kedua
            val bundle = Bundle()
            bundle.putString("nama", "Roni")
            bundle.putDouble("nilai", 10.0)
            val intent = Intent(this, HasilActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

        }
    }
}
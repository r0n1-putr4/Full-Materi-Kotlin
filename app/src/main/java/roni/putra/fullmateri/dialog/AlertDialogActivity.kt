package roni.putra.fullmateri.dialog

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import roni.putra.fullmateri.R

class AlertDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alert_dialog)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnDialog = findViewById<Button>(R.id.btnDialog)
        btnDialog.setOnClickListener {
            AlertDialog.Builder(this@AlertDialogActivity).apply {
                setTitle("Konfirmasi")
                setMessage("Apakah anda ingin melanjutkan?")
                setIcon(R.drawable.ic_calender)

                setPositiveButton("Yakin"){dialogInterface, i ->
                    dialogInterface.dismiss()
                }

                setNegativeButton("Batal"){dialogInterface, i->
                    dialogInterface.dismiss()
                }
            }.show()
        }
    }
}
package roni.putra.fullmateri.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import roni.putra.fullmateri.R

class LatWidgetActivity : AppCompatActivity() {
    private lateinit var pelanggan: Spinner
    private lateinit var btnSimpan: Button
    private lateinit var chkPokat: CheckBox
    private lateinit var etjlhPokat: EditText

    private lateinit var etJlhMangga: TextView
    private lateinit var chkMangga: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lat_widget)

        //inisialisasi
        pelanggan = findViewById(R.id.spPelanggan)
        btnSimpan = findViewById(R.id.btnSimpan)
        chkPokat = findViewById(R.id.chkPokat)
        etjlhPokat = findViewById(R.id.etjlhPokat)
        chkMangga = findViewById(R.id.chkMangga)
        etJlhMangga = findViewById(R.id.etJlhMangga)

        etjlhPokat.isEnabled = false
        etJlhMangga.isEnabled = false

    }

    override fun onStart() {
        super.onStart()
        //Paling fungsi proses
        proses()
        chkPokat.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etjlhPokat.isEnabled = true
            }
            else {
                etjlhPokat.setText("1")
                etjlhPokat.isEnabled = false
            }
        }
        chkMangga.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etJlhMangga.isEnabled = true
            }
            else {
                etJlhMangga.text = ""
                etJlhMangga.isEnabled = false
            }
        }
    }

    private fun proses(){
        btnSimpan.setOnClickListener {

            var totalBayar = 0.0
            if (chkPokat.isChecked){
                if (etjlhPokat.text.isEmpty()){
                    etjlhPokat.setText("1")
                }
                totalBayar += etjlhPokat.text.toString().toDouble() * 10000
            }else{
                etjlhPokat.setText("1")
            }

            if (chkMangga.isChecked){
                if (etJlhMangga.text.isEmpty()){
                    etJlhMangga.text = "1"
                }
                totalBayar += etJlhMangga.text.toString().toDouble() * 15000
            }else{
                etJlhMangga.text = ""
            }


            var dis = if (pelanggan.selectedItem.equals("Baru")) {
                0.10
            } else
                0.20

            var potongan = 0
            if (totalBayar>1000) potongan = 1000

            var totalSeluruh = totalBayar - (totalBayar * dis) - potongan
            var hasil = ""
            var tes: String? = null
            if (totalSeluruh>0){
                hasil = "Potongan Disc ${totalBayar * dis}\n" +
                        "Potongan Pembelian $potongan\n" +
                        "Total bayar $totalSeluruh"
                tes = """
                    |
                    |
                """.trimMargin()
            }else{
               hasil = "Silahkan Pilih Menu Minuman"
                tes = """
                    
                """.trimIndent()
            }

            Toast.makeText(applicationContext,"$hasil $tes",Toast.LENGTH_LONG).show()
        }
    }
}
package roni.putra.fullmateri.uts

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import roni.putra.fullmateri.R

class RoniActivity: AppCompatActivity() {
    private lateinit var tvTes: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar)

        tvTes = findViewById(R.id.tvTes)

        tvTes.text = "${unhex("58g888786858483",82)}\n${hex("123456",82)}"
    }

    private fun unhex(str: String = "", code: Int = 0): String {
        val parts = str.split("g") // Memisahkan string berdasarkan karakter 'g'
        if (parts.size < 2) return "" // Validasi input

        val head = parts[0].toInt(16) - code // Konversi bagian pertama ke bilangan desimal
        val content = parts[1] // Bagian kedua adalah konten

        if (head == content.length / 2) {
            var result = StringBuilder()

            for (i in 0 until head) {
                val hexPair = content.substring(i * 2, (i * 2) + 2) // Ambil 2 karakter heksadesimal
                val charValue = hexPair.toInt(16) - code // Konversi ke karakter dengan pengurangan kode
                result.append(charValue.toChar())
            }

            return result.reverse().toString() // Balikkan hasilnya
        }

        return ""
    }

    fun hex(str: String = "", code: Int = 0): String {
        if (code in 0..99) {
            val sb = StringBuilder()

            // Tambahkan panjang string + code dalam bentuk hexadecimal, diikuti "g"
            sb.append((str.length + code).toString(16)).append("g")

            // Balikkan string input
            val reversedStr = str.reversed()

            // Loop melalui setiap karakter dalam string
            for (char in reversedStr) {
                // Konversi karakter ke ASCII (ord), tambahkan code, lalu ubah ke hexadecimal
                sb.append((char.code + code).toString(16))
            }

            return sb.toString()
        }
        return ""
    }

}
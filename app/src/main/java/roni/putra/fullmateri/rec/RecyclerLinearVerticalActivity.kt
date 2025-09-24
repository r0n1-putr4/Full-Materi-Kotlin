package roni.putra.fullmateri.rec

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import roni.putra.fullmateri.R

class RecyclerLinearVerticalActivity : AppCompatActivity() {
    private lateinit var rvProduk: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_linear_vertical)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvProduk = findViewById(R.id.rvProduk)
    }

    override fun onStart() {
        super.onStart()
        setProduk()
    }

    private fun setProduk() {
        val produk = listOf<ProdukModel>(
            ProdukModel(R.drawable.makanan_satu, "Nama Produk 1", "Toko 1", "Rp. 12.000", "4.0"),
            ProdukModel(R.drawable.makanan_dua, "Nama Produk 2", "Toko 2", "Rp. 13.000", "3.7"),
            ProdukModel(R.drawable.makanan_tiga, "Nama Produk 3", "Toko 1", "Rp. 17.000", "4.4"),
            ProdukModel(R.drawable.makanan_empat, "Nama Produk 4", "Toko 3", "Rp. 18.000", "3.7"),
            ProdukModel(R.drawable.makanan_lima, "Nama Produk 5", "Toko 4", "Rp. 20.000", "4.7"),

            )

        //Panggil adapter
        val produkAdapter = ProdukAdapter(produk)
        rvProduk.adapter = produkAdapter
    }
}
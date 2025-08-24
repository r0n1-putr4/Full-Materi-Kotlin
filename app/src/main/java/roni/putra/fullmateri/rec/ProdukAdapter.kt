package roni.putra.fullmateri.rec

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import roni.putra.fullmateri.R

class ProdukAdapter(
    private val listItemProduk: List<ProdukModel>,
    private val type: Int
    ): RecyclerView.Adapter<ProdukAdapter.ViewProduk>() {

    private val typelinear = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewProduk {
        return if (type == typelinear){
            val view= LayoutInflater.from(parent.context).inflate(R.layout.list_item_produk_dua, parent,false )
            ViewProduk(view)
        }else{
            val view= LayoutInflater.from(parent.context).inflate(R.layout.list_item_produk, parent,false )
            ViewProduk(view)
        }

    }

    override fun onBindViewHolder(holder: ViewProduk, position: Int) {
        val produk = listItemProduk[position]
        holder.namaItem.text = produk.namaItem
        holder.txtKategori.text = produk.namaKategori
        holder.imgItem.setImageResource(produk.gambar)
        if (produk.gambar == 1){
            holder.namaItem.visibility = View.GONE
        }

    }

    override fun getItemCount() = listItemProduk.size

    class ViewProduk(view: View): RecyclerView.ViewHolder(view){
        val namaItem: TextView = view.findViewById<TextView>(R.id.namaItem)
        val txtKategori: TextView = view.findViewById<TextView>(R.id.txtKategori)
        val imgItem: ImageView = view.findViewById<ImageView>(R.id.imgItem)
    }
}
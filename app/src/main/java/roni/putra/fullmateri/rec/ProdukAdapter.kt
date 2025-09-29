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
    val listener: OnAdapterListener
) : RecyclerView.Adapter<ProdukAdapter.ViewProduk>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewProduk {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_produk, parent, false)
        return ViewProduk(view)
    }

    override fun onBindViewHolder(holder: ViewProduk, position: Int) {
        val data = listItemProduk[position]
        holder.imgProduk.setImageResource(data.gambar)
        holder.tvNamaProduk.text = data.namaProduk
        holder.tvToko.text = data.toko
        holder.tvHarga.text = data.harga
        holder.tvRating.text = data.rating

        holder.itemView.setOnClickListener {
            listener.onClick(data)
        }

    }

    override fun getItemCount() = listItemProduk.size

    class ViewProduk(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduk = view.findViewById<ImageView>(R.id.imgProduk)
        val tvNamaProduk = view.findViewById<TextView>(R.id.tvNamaProduk)
        val tvToko = view.findViewById<TextView>(R.id.tvToko)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val tvRating = view.findViewById<TextView>(R.id.tvRating)
    }

    interface OnAdapterListener {
        fun onClick(results: ProdukModel)
    }
}
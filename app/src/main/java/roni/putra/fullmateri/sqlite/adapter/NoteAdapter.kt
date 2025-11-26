package roni.putra.fullmateri.sqlite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import roni.putra.fullmateri.R
import roni.putra.fullmateri.rec.ProdukAdapter.OnAdapterListener
import roni.putra.fullmateri.sqlite.model.Note

class NoteAdapter(
    private val listNote: List<Note>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<NoteAdapter.ViewProduk>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewProduk {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewProduk(view)
    }

    override fun onBindViewHolder(
        holder: ViewProduk,
        position: Int
    ) {
        val data = listNote[position]
        holder.tvTitle.text = data.judul
        holder.tvBody.text = data.isi

        holder.itemView.setOnClickListener {
            listener.onClick(data)
        }

        // Event Long Klik
        holder.itemView.setOnLongClickListener {
            listener.onLongClick(data,position)
            true // penting agar long click tidak pemicu click juga
        }
    }

    override fun getItemCount(): Int = listNote.size

    class ViewProduk(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvBody = view.findViewById<TextView>(R.id.tvBody)

    }

    interface OnAdapterListener {
        fun onClick(data: Note)
        fun onLongClick(data: Note,position: Int)
    }

}
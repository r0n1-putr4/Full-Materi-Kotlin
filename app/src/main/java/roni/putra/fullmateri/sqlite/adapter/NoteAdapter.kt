package roni.putra.fullmateri.sqlite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import roni.putra.fullmateri.R
import roni.putra.fullmateri.sqlite.model.Note

class NoteAdapter(
    private val listNote: List<Note>
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
        holder.txtTitle.text = data.judul
        holder.txtBody.text = data.isi
    }

    override fun getItemCount(): Int = listNote.size

    class ViewProduk(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtBody = view.findViewById<TextView>(R.id.txtBody)

    }
}
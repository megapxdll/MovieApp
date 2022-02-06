package com.example.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemHistoryListBinding
import com.example.movieapp.model.entities.Content

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    private var data: List<Content> = arrayListOf()

    fun setData(data: List<Content>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            ItemHistoryListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class RecyclerItemViewHolder(private val binding: ItemHistoryListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Content) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                recyclerViewItem.text =
                    String.format("%s %d %s", data.name.get(0), data.id)
                root.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "on click: ${data.name.get(0)}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
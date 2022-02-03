package com.example.movieapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FragmentItempageBinding
import com.example.movieapp.model.entities.Content
import com.example.movieapp.ui.main.MainFragment

class MainFragmentAdapter(private val itemClickListener: MainFragment.OnItemViewClickListener)
    : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var contentData: List<Content> = listOf()
    private lateinit var binding: FragmentItempageBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setContent(data: List<Content>) {
        contentData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = FragmentItempageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(contentData[position])
    }

    override fun getItemCount() = contentData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(content: Content) = with(binding) {
            mainFragmentRecyclerItemTextView?.text = content.id.toString()
            mainFragmentRecyclerItemTextViewDesc?.text = content.name
            root.setOnClickListener { itemClickListener.onItemViewClick(content) }
        }
    }
}
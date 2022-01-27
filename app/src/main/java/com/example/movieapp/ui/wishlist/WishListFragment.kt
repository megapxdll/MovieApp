package com.example.movieapp.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentWishlistBinding
import com.example.movieapp.databinding.ItemWishlistBinding
import com.example.movieapp.ui.itemPage.ItemPageViewModel

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
class WishListFragment : Fragment() {

    private lateinit var wishListViewModel: WishListViewModel
    private lateinit var _binding: FragmentWishlistBinding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        wishListViewModel = ViewModelProvider(this).get(WishListViewModel::class.java)
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerviewWishlist
        val adapter = FilmsAdapter()
        recyclerView.adapter = adapter
        wishListViewModel.texts.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        root.setOnClickListener(View.OnClickListener {
            R.layout.fragment_itempage;
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }

    class FilmsAdapter :
        ListAdapter<String, WishListViewHolder>(object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }) {

        private val drawables = listOf(
            R.drawable.avatar_1,
            R.drawable.avatar_2,
            R.drawable.avatar_3,
            R.drawable.avatar_4,
            R.drawable.avatar_5,
            R.drawable.avatar_6,
            R.drawable.avatar_7,
            R.drawable.avatar_8,
            R.drawable.avatar_9,
            R.drawable.avatar_10,
            R.drawable.avatar_11,
            R.drawable.avatar_12,
            R.drawable.avatar_13,
            R.drawable.avatar_14,
            R.drawable.avatar_15,
            R.drawable.avatar_16,
        )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
            val binding = ItemWishlistBinding.inflate(LayoutInflater.from(parent.context))
            return WishListViewHolder(binding)
        }

        override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
            holder.textView.text = getItem(position)
            holder.imageView.setImageDrawable(
                ResourcesCompat.getDrawable(holder.imageView.resources, drawables[position], null)
            )
        }
    }

    class WishListViewHolder(binding: ItemWishlistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imageView: ImageView = binding.imageViewItemWishlist
        val textView: TextView = binding.textViewItemWishlist
    }
}
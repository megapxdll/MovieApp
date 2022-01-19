package com.example.movieapp.ui.itemPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.FragmentItempageBinding

class ItemPageFragment : Fragment() {

    private lateinit var itemPageViewModel: ItemPageViewModel
    private var _binding: FragmentItempageBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemPageViewModel =
            ViewModelProvider(this).get(ItemPageViewModel::class.java)

        _binding = FragmentItempageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
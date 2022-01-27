package com.example.movieapp.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.FragmentFilmsBinding

class FilmsFragment : Fragment() {

    private lateinit var filmsViewModel: FilmsViewModel
    private var _binding: FragmentFilmsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        filmsViewModel =
            ViewModelProvider(this).get(FilmsViewModel::class.java)

        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
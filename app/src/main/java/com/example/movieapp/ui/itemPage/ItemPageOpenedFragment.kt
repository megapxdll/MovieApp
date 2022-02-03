package com.vladimir.akotlinweather.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.model.entities.Content
import com.example.movieapp.databinding.FragmentItemPageOpenedBinding

class ItemPageOpenedFragment : Fragment() {
    private var _binding: FragmentItemPageOpenedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemPageOpenedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Content>(BUNDLE_EXTRA)?.let {
            with(binding) {
                textViewItemTitle.text = it.id.toString()
                textViewItemDesc.text = it.name
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_EXTRA = "content"

        fun newInstance(bundle: Bundle): ItemPageOpenedFragment {
            val fragment = ItemPageOpenedFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
package com.example.movieapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentItemPageOpenedBinding
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.model.AppState
import com.example.movieapp.model.entities.Content
import com.example.movieapp.ui.adapters.MainFragmentAdapter
import com.example.movieapp.ui.itemPage.ItemPageFragment
import com.example.movieapp.ui.itemPage.ItemPageViewModel
import com.google.android.material.snackbar.Snackbar
import com.vladimir.akotlinweather.ui.details.ItemPageOpenedFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by sharedViewModel()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private var adapter: MainFragmentAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainFragmentFilmsRecyclerView.adapter = adapter
            viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
            viewModel.getWishListContent()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                progressBar.visibility = View.GONE
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(content: Content) {
                        val manager = activity?.supportFragmentManager
                        manager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(ItemPageOpenedFragment.BUNDLE_EXTRA, content)
                            }
                            manager.beginTransaction()
                                .add(R.id.container, ItemPageOpenedFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }).apply {
                    setContent(appState.contentData)
                }
                mainFragmentFilmsRecyclerView.adapter = adapter
            }
            is AppState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                progressBar.visibility = View.GONE
                val snackbar = Snackbar
                    .make(this.mainFragmentRootView, R.string.error, Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(content: Content)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
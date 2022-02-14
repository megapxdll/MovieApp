package com.example.movieapp.ui.main

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentItemPageOpenedBinding
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.model.AppState
import com.example.movieapp.model.entities.Content
import com.example.movieapp.model.entities.GenresApi
import com.example.movieapp.ui.adapters.MainFragmentAdapter
import com.example.movieapp.ui.itemPage.ItemPageFragment
import com.example.movieapp.ui.itemPage.ItemPageViewModel
import com.google.android.material.snackbar.Snackbar
import com.vladimir.akotlinweather.ui.details.ItemPageOpenedFragment
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment(), CoroutineScope by MainScope(){
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
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val onLocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            context?.let {
                getAddressAsync(it, location)
            }
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    private val permissionResult = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            getLocation()
        } else {
            Toast.makeText(
                context,
                getString(R.string.dialog_message_no_gps),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            getLocation()
        } else {
            permissionResult.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun getAddressAsync(context: Context, location: Location) {
        val geoCoder = Geocoder(context)
        launch {
            val job = async(Dispatchers.IO) {
                geoCoder.getFromLocation(location.latitude, location.longitude, 1)
            }
            val addresses = job.await()
            showAddressDialog(addresses[0].getAddressLine(0), location)
        }
    }

    private fun showAddressDialog(address: String, location: Location) {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.dialog_address_title))
                .setMessage(address)
                .setPositiveButton(getString(R.string.common_google_play_services_enable_text)) { _, _ ->
                    ItemPageOpenedFragment(
                    )
                }
                .setNegativeButton(getString(R.string.dialog_button_close)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
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

                    override fun onDataEnding(from: Int, sizeToRequest: Int) {
                        TODO("Not yet implemented")
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

        fun onDataEnding(from: Int, sizeToRequest: Int)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
package nl.mk.venuehelper.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionManager
import nl.mk.venuehelper.R
import nl.mk.venuehelper.database.models.VenueDb
import nl.mk.venuehelper.databinding.FragmentDetailsVenueBinding
import nl.mk.venuehelper.shared.Resource
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailsVenueFragment : Fragment() {

    private lateinit var binding: FragmentDetailsVenueBinding

    private val viewModel: DetailsVenueViewModel by viewModel()
    private val args: DetailsVenueFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsVenueBinding.inflate(inflater, container, false)
        showVenue(args.venue) //display some fields
        initObserver()
        return binding.root
    }

    private fun initObserver() {
        viewModel
            .requestVenueDetails(args.venue)
            .observe(viewLifecycleOwner) { resource ->
                binding.progressBar.isVisible = resource is Resource.Loading
                when (resource) {
                    is Resource.Success -> {
                        showVenue(resource.data)
                    }
                    is Resource.Error -> {
                        showError(resource)
                    }
                    else -> {
                        //do nothing
                        Timber.d("do nothing with $resource")
                    }
                }
            }
    }

    private fun showError(resource: Resource.Error) {
        val msg = if (resource.isNetworkError) {
            resources.getString(R.string.network_error)
        } else {
            resource.exception.message
        }
        Toast.makeText(
            requireContext(), msg,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showVenue(model: VenueDb) {
        TransitionManager.beginDelayedTransition(binding.container)
        binding.venue = model
    }


}
package nl.mk.venuehelper.ui.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import nl.mk.venuehelper.R
import nl.mk.venuehelper.database.models.VenueDb
import nl.mk.venuehelper.databinding.FragmentVenuesListBinding
import nl.mk.venuehelper.shared.Resource
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class VenuesListFragment : Fragment() {

    private lateinit var binding: FragmentVenuesListBinding

    private val viewModel: VenuesListViewModel by viewModel()
    private val venuesAdapter = VenuesAdapter(this::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVenuesListBinding.inflate(inflater, container, false)

        initView()
        initObserver()
        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_venues, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.apply {
            queryHint = getString(R.string.venue)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.setQuery(newText)
                    return true
                }
            })
        }
    }

    private fun initObserver() {
        viewModel.venues.observe(viewLifecycleOwner) { resource ->
            binding.swipeRefreshLayout.isRefreshing = resource is Resource.Loading
            when (resource) {
                is Resource.Success -> {
                    showResults(resource.data)
                }
                is Resource.Empty -> {
                    showEmptyView()
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

    private fun showEmptyView() {
        with(binding) {
            TransitionManager.beginDelayedTransition(binding.root)
            recyclerView.isVisible = false
            noDataGroup.isVisible = true
        }
        venuesAdapter.submitList(emptyList())
    }

    private fun showResults(data: List<VenueDb>) {
        with(binding) {
            TransitionManager.beginDelayedTransition(binding.root)
            recyclerView.isVisible = true
            noDataGroup.isVisible = false
        }
        venuesAdapter.submitList(data)
    }

    private fun initView() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = venuesAdapter
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun onItemClicked(position: Int) {
        val clickedVenue = venuesAdapter.currentList[position]
        val direction = VenuesListFragmentDirections.venuesListToDetails(clickedVenue)
        findNavController().navigate(direction)
    }

}
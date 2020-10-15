package nl.mk.venuehelper.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.mk.venuehelper.database.models.VenueDb
import nl.mk.venuehelper.databinding.ListItemVenueBinding

class VenuesAdapter(
    private val onItemClicked: (Int) -> Unit
) : ListAdapter<VenueDb, VenuesAdapter.VenueItemViewHolder>(VenueDbDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueItemViewHolder {
        val binding = ListItemVenueBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VenueItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VenueItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }

    class VenueItemViewHolder(private val binding: ListItemVenueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: VenueDb, onItemClicked: (Int) -> Unit) {
            binding.venue = model
            binding.root.setOnClickListener {
                onItemClicked(adapterPosition)
            }
            binding.executePendingBindings()
        }
    }
}


private class VenueDbDiffCallback : DiffUtil.ItemCallback<VenueDb>() {

    override fun areItemsTheSame(oldItem: VenueDb, newItem: VenueDb): Boolean {
        return oldItem.venueId == newItem.venueId
    }

    override fun areContentsTheSame(oldItem: VenueDb, newItem: VenueDb): Boolean {
        return oldItem == newItem
    }
}
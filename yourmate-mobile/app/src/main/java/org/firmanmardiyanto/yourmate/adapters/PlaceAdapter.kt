package org.firmanmardiyanto.yourmate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.firmanmardiyanto.yourmate.R
import org.firmanmardiyanto.yourmate.databinding.LayoutItemPlaceBinding
import org.firmanmardiyanto.yourmate.diff_utils.PlaceDiffUtil
import org.firmanmardiyanto.yourmate.domain.model.Place

class PlaceAdapter : ListAdapter<Place, PlaceAdapter.PlaceViewHolder>(PlaceDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding =
            LayoutItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class PlaceViewHolder(private val binding: LayoutItemPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            with(binding) {
                tvRating.text = place.rating.toString()
                Glide.with(itemView.context)
                    .load(place.imageUrl)
                    .error(R.drawable.ic_image)
                    .into(ivThumbnailPlace)
                tvTitlePlace.text = place.title
                tvLocationPlace.text = place.location
                tvBodyPlace.text = place.desc
                tvCategory.text = place.category
            }
        }
    }

}
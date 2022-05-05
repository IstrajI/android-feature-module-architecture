package com.nikitin.ui_search.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikitin.ui_search.databinding.SearchRepositoryItemBinding
import com.nikitin.ui_search.repository.model.RepositoryUIModel

class SearchRepositoryAdapter(private val onItemClicked: (String) -> Unit  ): ListAdapter<RepositoryUIModel, SearchRepositoryAdapter.SearchRepositoryItemViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRepositoryItemViewHolder {
        val itemBinding = SearchRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchRepositoryItemViewHolder(itemBinding, onItemClicked)
    }

    override fun onBindViewHolder(holder: SearchRepositoryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchRepositoryItemViewHolder(private val binding: SearchRepositoryItemBinding, private val onItemClicked: (String) -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepositoryUIModel) {
            binding.apply {
                name.text = item.name
                if (item.description != null) {
                    description.text = item.description
                } else
                description.text = item.description
                description.isVisible = item.description != null
                stars.text = item.stars
                language.text = item.language
                language.isVisible = item.language != null
                license.text = item.licenseName
                license.isVisible = item.licenseName != null
                updated.text = item.updated
                updated.isVisible = item.updated != null
                itemView.setOnClickListener {onItemClicked(item.url)}

            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<RepositoryUIModel>() {
        override fun areItemsTheSame(oldItem: RepositoryUIModel, newItem: RepositoryUIModel) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: RepositoryUIModel, newItem: RepositoryUIModel) = oldItem == newItem
    }
}
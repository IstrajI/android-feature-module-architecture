package com.nikitin.githubsearchchallenge.feature.repositorysearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikitin.githubsearchchallenge.databinding.SearchRepositoryItemBinding

class RepositorySearchAdapter: ListAdapter<RepositorySearchUIModel, RepositorySearchAdapter.SearchRepositoryItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRepositoryItemViewHolder {
        val itemBinding = SearchRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchRepositoryItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchRepositoryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchRepositoryItemViewHolder(private val binding: SearchRepositoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepositorySearchUIModel) {
            binding.apply {
                name.text = item.name
                description.text = item.description
                stars.text = item.stars.toString()
                language.text = item.language
                license.text = item.licenseName
                updated.text = item.updated
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<RepositorySearchUIModel>() {
        override fun areItemsTheSame(oldItem: RepositorySearchUIModel, newItem: RepositorySearchUIModel) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: RepositorySearchUIModel, newItem: RepositorySearchUIModel) = oldItem == newItem
    }
}
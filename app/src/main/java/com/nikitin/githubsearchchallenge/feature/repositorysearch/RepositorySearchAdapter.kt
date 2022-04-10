package com.nikitin.githubsearchchallenge.feature.repositorysearch

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikitin.githubsearchchallenge.databinding.SearchRepositoryItemBinding
import com.nikitin.githubsearchchallenge.feature.repositorysearch.entity.RepositoryUIModel

class RepositorySearchAdapter: ListAdapter<RepositoryUIModel, RepositorySearchAdapter.SearchRepositoryItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRepositoryItemViewHolder {
        val itemBinding = SearchRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchRepositoryItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchRepositoryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchRepositoryItemViewHolder(private val binding: SearchRepositoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RepositoryUIModel) {
            binding.apply {
                name.text = item.name
                description.text = item.description
                stars.text = item.stars.toString()
                language.text = item.language
                license.text = item.licenseName
                updated.text = item.updated
                itemView.setOnClickListener {
                    val intent =
                        Intent(Intent.ACTION_VIEW).setData(Uri.parse(item.url))
                    startActivity(binding.root.context, intent, null)
                }
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<RepositoryUIModel>() {
        override fun areItemsTheSame(oldItem: RepositoryUIModel, newItem: RepositoryUIModel) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: RepositoryUIModel, newItem: RepositoryUIModel) = oldItem == newItem
    }
}
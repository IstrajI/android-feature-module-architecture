package com.nikitin.githubsearchchallenge.presentation.search.repository

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nikitin.githubsearchchallenge.databinding.SearchRepositoryItemBinding
import com.nikitin.githubsearchchallenge.presentation.search.model.RepositoryUIModel

class RepositorySearchAdapter: ListAdapter<RepositoryUIModel, RepositorySearchAdapter.SearchRepositoryItemViewHolder>(
    DiffCallback()
) {

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
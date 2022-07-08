package com.example.rickandmorty.presentation.characters.ui.characters_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.databinding.CharactersGridViewItemBinding
import com.example.rickandmorty.presentation.characters.model.CharacterListItem

class CharactersGridAdapter (
    private val clickListener: (Int) -> Unit
) :
    PagingDataAdapter<CharacterListItem, CharactersGridAdapter.GridItemViewHolder>(DiffCallback) {

    class GridItemViewHolder (
        private val binding: CharactersGridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterListItem,
                 clickListener: (Int) -> Unit) {
            binding.apply {
                characterImage.load(item.image)
                name.text = item.name
                species.text = item.species
                status.text = item.status
                gender.text = item.gender
                character.setOnClickListener { clickListener(item.id) }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CharacterListItem>() {
        override fun areItemsTheSame(oldItem: CharacterListItem, newItem: CharacterListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterListItem, newItem: CharacterListItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        return GridItemViewHolder(
            CharactersGridViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, clickListener) }
    }

}
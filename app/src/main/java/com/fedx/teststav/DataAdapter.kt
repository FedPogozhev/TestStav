package com.fedx.teststav

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fedx.teststav.databinding.ItemForRvBinding
import com.fedx.teststav.network.Character

class DataAdapter() : ListAdapter<Character, DataAdapter.CharacterViewHolder>(DiffCallback) {

    class CharacterViewHolder(private var binding: ItemForRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                tvName.text = character.name
                tvSpecies.text = character.species + ","
                tvGender.text = character.gender
                tvStatus.text = character.status
                this.character = character

                when (character.status) {
                    "Alive" -> {
                        tvStatus.apply {
                            background = resources.getDrawable(R.drawable.roundrect)
                            setTextColor(resources.getColor(R.color.green))
                        }
                    }
                    "Dead" -> {
                        tvStatus.apply {
                            background = resources.getDrawable(R.drawable.roun_red)
                            setTextColor(Color.RED)
                        }
                    }
                    "unknown" -> {
                        tvStatus.apply {
                            background = resources.getDrawable(R.drawable.roun_gray)
                            setTextColor(Color.GRAY)
                        }
                    }
                }
                executePendingBindings()
            }

        }

        companion object {
            fun from(parent: ViewGroup): CharacterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemForRvBinding.inflate(layoutInflater, parent, false)
                return CharacterViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemForRvBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val src = getItem(position)
        holder.bind(src)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
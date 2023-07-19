package com.example.main.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.common.model.SlotsResponseItem
import com.example.main.R
import com.example.main.databinding.SlotsLayoutBinding

class SlotsAdapter(val slots: List<SlotsResponseItem>) :
    RecyclerView.Adapter<SlotsAdapter.SlotsViewHolder>() {

    // Item onclick
    lateinit var onSlotItemClick: ((SlotsResponseItem) -> Unit)

    // Selected item position
    private var selectedItemPosition = RecyclerView.NO_POSITION

    inner class SlotsViewHolder(val binding: SlotsLayoutBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(slot: SlotsResponseItem) {
            binding.slotsTv.text = slot.name

            // Update the item view's background color based on the selected item position
            // Set the border color based on the selected item position
            if (adapterPosition == selectedItemPosition) {
                val borderColor = ContextCompat.getColor(binding.root.context, R.color.slot)
                binding.root.setBackgroundResource(R.drawable.selected_slot_border)
                binding.root.backgroundTintList = ColorStateList.valueOf(borderColor)
            } else {
                val borderColor = ContextCompat.getColor(binding.root.context, R.color.black)
                binding.root.setBackgroundResource(R.drawable.unselected_slot_border)
                binding.root.backgroundTintList = ColorStateList.valueOf(borderColor)
            }

            // Set click listener on the item view
            binding.root.setOnClickListener {
                val clickedSlot = slots[adapterPosition]
                // Update the selected item position
                selectedItemPosition = adapterPosition

                // Notify the adapter that the data set has changed
                notifyDataSetChanged()

                // Call the item click listener
                onSlotItemClick.invoke(clickedSlot)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotsViewHolder {
        return SlotsViewHolder(
            SlotsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            parent.context
        )
    }

    override fun getItemCount() = slots.size

    override fun onBindViewHolder(holder: SlotsViewHolder, position: Int) {
        holder.bind(slots[position])
    }

    // Method to set the selected item position
    fun setSelectedItemPosition(position: Int) {
        selectedItemPosition = position
    }
}


package com.example.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.common.model.ParkingResponse
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.example.main.R
import com.example.main.databinding.PakingLayoutBinding
import com.squareup.picasso.Picasso

class ParkingAdaptor(private val parkings: ArrayList<ParkingResponseItem>):
RecyclerView.Adapter<ParkingAdaptor.ParkingViewHolder>(){
    //itemclick
    lateinit var onItemClick: ((ParkingResponseItem) -> Unit)

    inner class ParkingViewHolder(val binding: PakingLayoutBinding, val context: Context) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            parking: ParkingResponseItem
        ){
            binding.apply {
                parkingTitleTv.text = parking.title
                parkingLocationTv.text = parking.location
                parkingCostTv.text = parking.price.toString()

                if (parking.image.isNotEmpty()){
                    Picasso.get().load(parking.image).into(binding.parkingLayoutImg)
                }else {
                    binding.parkingLayoutImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.parking_img))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        return ParkingViewHolder(
            PakingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context
        )
    }

    override fun getItemCount(): Int = parkings.size

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        holder.bind(parkings[position])

        //click item
        holder.itemView.setOnClickListener {
            onItemClick.invoke(parkings[position])
        }
    }


}

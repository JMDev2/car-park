package com.example.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.common.model.ParkingResponseItem
import com.ekenya.rnd.common.model.PaymentMode
import com.example.main.databinding.ItemPaymentModeLayoutBinding
import com.example.main.ui.payment.PaymentViewModel
import com.squareup.picasso.Picasso

class PaymentModeAdapter(private val paymentModes: List<PaymentMode>, private val viewModel: PaymentViewModel) :
    RecyclerView.Adapter<PaymentModeAdapter.ViewHolder>() {

    //itemclick
    lateinit var onItemClick: ((PaymentMode) -> Unit)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPaymentModeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paymentMode = paymentModes[position]
        holder.bind(paymentMode)

        //click item
        holder.itemView.setOnClickListener {
            onItemClick.invoke(paymentModes[position])
        }
    }

    override fun getItemCount(): Int {
        return paymentModes.size
    }

    inner class ViewHolder(private val binding: ItemPaymentModeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(paymentMode: PaymentMode) {
            Picasso.get().load(paymentMode.image).into(binding.peymentMpesaImageLayout)
            binding.userPhoneNumberTvLayout.text = viewModel.userInput.value
        }
    }
}

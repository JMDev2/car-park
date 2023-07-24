package com.example.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.common.model.CardPaymentMode
import com.ekenya.rnd.common.model.MpesaPaymentMode
import com.example.main.databinding.ItemPaymentModeLayoutBinding
import com.example.main.ui.payment.PaymentViewModel
import com.squareup.picasso.Picasso

class PaymentModeAdapter(private val mpesaPaymentModes: List<MpesaPaymentMode>, private val viewModel: PaymentViewModel) :
    RecyclerView.Adapter<PaymentModeAdapter.ViewHolder>() {

    //itemclick
    lateinit var onItemClick: ((MpesaPaymentMode) -> Unit)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPaymentModeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paymentMode = mpesaPaymentModes[position]
        holder.bind(paymentMode)

        //click item
        holder.itemView.setOnClickListener {
            onItemClick.invoke(mpesaPaymentModes[position])
        }
    }

    override fun getItemCount(): Int {
        return mpesaPaymentModes.size
    }

    inner class ViewHolder(private val binding: ItemPaymentModeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mpesaPaymentMode: MpesaPaymentMode) {
            Picasso.get().load(mpesaPaymentMode.image).into(binding.peymentMpesaImageLayout)
            binding.userPhoneNumberTvLayout.text = viewModel.phoneInput.value

        }
    }
}

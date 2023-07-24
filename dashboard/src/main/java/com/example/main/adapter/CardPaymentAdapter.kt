package com.example.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekenya.rnd.common.model.CardPaymentMode
import com.ekenya.rnd.common.model.MpesaPaymentMode
import com.example.main.databinding.CardPaymentLayoutBinding
import com.example.main.databinding.ItemPaymentModeLayoutBinding
import com.example.main.ui.payment.PaymentViewModel
import com.squareup.picasso.Picasso

class CardPaymentAdapter (private val cardPaymentMode: List<CardPaymentMode>, private val viewModel: PaymentViewModel) :
    RecyclerView.Adapter<CardPaymentAdapter.ViewHolder>() {

    //itemclick
    lateinit var onItemClick: ((CardPaymentMode) -> Unit)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardPaymentAdapter.ViewHolder {
        val binding = CardPaymentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       return ViewHolder(binding)
    }

    
    override fun onBindViewHolder(holder: CardPaymentAdapter.ViewHolder, position: Int) {
        val cardPayment = cardPaymentMode[position]

        holder.bind(cardPayment)

        //click item
        holder.itemView.setOnClickListener {
            onItemClick.invoke(cardPaymentMode[position])
        }
    }


    override fun getItemCount(): Int {
        return cardPaymentMode.size
    }

    inner class ViewHolder(private val binding: CardPaymentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cardPaymentMode: CardPaymentMode) {
            Picasso.get().load(cardPaymentMode.image).into(binding.peymentCardImageLayout)
            binding.userCardNumberTvLayout.text = viewModel.cardInput.value

        }
    }
}

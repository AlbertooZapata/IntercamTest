package com.intercamtest.zapata.ui.payments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.intercamtest.zapata.base.BaseViewHolder
import com.intercamtest.zapata.data.model.Payment
import com.intercamtest.zapata.databinding.ItemPaymentBinding

/**
 * Created by Alberto Zapata on ago, 2021
 */
class PaymentsAdapter(
    private val context: Context,
    private val paymentsList: List<Payment>,
    private val itemClickListener: OnPaymentClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val classTag = "myLog [${javaClass.simpleName}]"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        val itemPaymentBinding =
            ItemPaymentBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = PaymentViewHolder(itemPaymentBinding)
        itemPaymentBinding.root.setOnClickListener {
            val position =
                holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
            itemClickListener.onItemClick(paymentsList[position])
        }
        return holder
    }

    override fun getItemCount() = paymentsList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

        when (holder) {
            is PaymentViewHolder -> {
                holder.bind(paymentsList[position])
            }
        }
    }

    inner class PaymentViewHolder(private val itemPaymentBinding: ItemPaymentBinding) :
        BaseViewHolder<Payment>(itemPaymentBinding.root) {
        override fun bind(item: Payment) = with(itemPaymentBinding) {

            itemPaymentBinding.txtDescription.text = item.description
            itemPaymentBinding.txtQuantity1.text = item.quantity1.toString()
            itemPaymentBinding.txtQuantity2.text = item.quantity2.toString()
            if (item.isAuthorized) {
                itemPaymentBinding.txtIsAuthorized.visibility = View.VISIBLE
            }
        }
    }

    interface OnPaymentClickListener {
        fun onItemClick(payment: Payment)
    }
}
package com.intercamtest.zapata.ui.beers

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import coil.api.load
import com.intercamtest.zapata.base.BaseViewHolder
import com.intercamtest.zapata.data.model.Beer
import com.intercamtest.zapata.databinding.ItemBeerBinding

/**
 * Created by Alberto Zapata on ago, 2021
 */
class BeerAdapter(
    private val context: Context,
    private val berList: List<Beer>,
    private val itemClickListener: OnBeerClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val classTag = "myLog [${javaClass.simpleName}]"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        val itemBeerBinding = ItemBeerBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = BeerViewHolder(itemBeerBinding)
        itemBeerBinding.root.setOnClickListener {
            val position =
                holder.adapterPosition.takeIf { it != NO_POSITION } ?: return@setOnClickListener
            itemClickListener.onItemClick(berList[position])
        }
        return holder
    }

    override fun getItemCount() = berList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

        when (holder) {
            is BeerViewHolder -> {
                holder.bind(berList[position])
            }
        }
    }

    inner class BeerViewHolder(private val itemBeerBinding: ItemBeerBinding) :
        BaseViewHolder<Beer>(itemBeerBinding.root) {
        override fun bind(item: Beer) = with(itemBeerBinding) {

            Log.i(classTag, item.toString())
            itemBeerBinding.imgBeer.load(item.image_url)
            itemBeerBinding.txtName.text = item.name
            itemBeerBinding.txtTagLine.text = item.tagline
        }
    }

    interface OnBeerClickListener {
        fun onItemClick(beer: Beer)
    }
}
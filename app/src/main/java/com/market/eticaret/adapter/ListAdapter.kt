package com.market.eticaret.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.market.eticaret.R
import com.market.eticaret.databinding.RecyclerItemBinding
import com.market.eticaret.model.Product
import com.market.eticaret.view.ListFragmentDirections


class ListAdapter(var productList:ArrayList<Product>):RecyclerView.Adapter<com.market.eticaret.adapter.ListAdapter.ListViewHolder>() {
    class ListViewHolder(val binding: RecyclerItemBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ListViewHolder {
        val binding=RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    fun productListesiniGuncelle(yeniProductListesi: List<Product>){
        productList.clear()
        productList.addAll(yeniProductListesi)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder:ListViewHolder, position: Int) {

        holder.binding.recyclerNameText.setText(productList[position].title)
        holder.binding.recyclerPriceText.setText(productList[position].price.toString()+"$")

        Glide.with(holder.itemView.context) //Image,recycler itemda olduğu için bu contexti veririz.

            .load(productList[position].thumbNail)

            .override(500, 500)

            .error(R.drawable.ic_launcher_background)

            .into(holder.binding.imageView3)

        holder.itemView.setOnClickListener{
            val action=ListFragmentDirections.actionListFragmentToDetailFragment(product = productList[position]
            )
            Navigation.findNavController(it).navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
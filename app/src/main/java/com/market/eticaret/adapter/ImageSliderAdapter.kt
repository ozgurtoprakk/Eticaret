package com.market.eticaret.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.market.eticaret.R
import com.market.eticaret.adapter.ListAdapter.ListViewHolder
import com.market.eticaret.databinding.ImageItemBinding
import com.market.eticaret.databinding.RecyclerItemBinding

class ImageSliderAdapter(
    private val imagesArray: List<String?>,
    private val context: Context
) : RecyclerView.Adapter<ImageSliderAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imagesArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            Glide.with(context)
                .load(imagesArray[position])
                //.placeholder(R.drawable.gif) // Yüklenirken gösterilecek resim
                .thumbnail(Glide.with(context).load(R.drawable.gif)) //Placeholder gif ise bu şekilde kullanılır.
                .error(R.drawable.ic_launcher_background) // Hata olursa gösterilecek resim
                .into(holder.binding.imageView4)
    }
}
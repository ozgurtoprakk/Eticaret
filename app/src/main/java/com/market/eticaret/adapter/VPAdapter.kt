package com.market.eticaret.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.market.eticaret.model.Product
import com.market.eticaret.model.Review
import com.market.eticaret.view.CommentFragment
import com.market.eticaret.view.DetailFragment
import com.market.eticaret.view.InfoFragment

public open class VPAdapter(fa: DetailFragment,val review:List<Review>,val product:Product) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> InfoFragment()
            1 -> CommentFragment()
            else -> InfoFragment()
        }

        val bundle = Bundle()
        bundle.putParcelableArrayList("reviews", ArrayList(review)) //bizim yollamak istediğimiz veri args.product bir Product türündendir.
        bundle.putParcelable("product", product)
        fragment.arguments = bundle

        return fragment
    }
}
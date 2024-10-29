package com.market.eticaret.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.market.eticaret.R
import com.market.eticaret.databinding.FragmentDetailBinding
import com.market.eticaret.databinding.FragmentInfoBinding
import com.market.eticaret.model.Product
import com.market.eticaret.model.Review

open class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = arguments?.getParcelable<Product>("product")
        binding.categoryText.text="Category:"+product!!.category.toString()
        binding.priceText.text="Price:"+product!!.price.toString()+"$"
        binding.titleText.text="Title:"+product!!.title.toString()





    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
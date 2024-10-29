package com.market.eticaret.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.market.eticaret.R
import com.market.eticaret.adapter.CommentAdapter
import com.market.eticaret.databinding.FragmentCommentBinding
import com.market.eticaret.databinding.FragmentDetailBinding
import com.market.eticaret.model.Product
import com.market.eticaret.model.Review
import com.market.eticaret.viewmodel.ListViewModel

class CommentFragment : Fragment() {
    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reviews = arguments?.getParcelableArrayList<Review>("reviews")
        binding.recyclerView2.layoutManager = LinearLayoutManager(context)
        binding.recyclerView2.adapter= reviews?.let { CommentAdapter(it) }



    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
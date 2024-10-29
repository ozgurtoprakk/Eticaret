package com.market.eticaret.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.market.eticaret.R
import com.market.eticaret.adapter.ImageSliderAdapter
import com.market.eticaret.adapter.VPAdapter
import com.market.eticaret.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    val args: DetailFragmentArgs by navArgs()
    lateinit var imageSliderAdapter: ImageSliderAdapter
    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val toolbar = binding.toolbar3
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail"
        }

        setHasOptionsMenu(true)

        val mViewPager: ViewPager2 = binding.viewPager
        val mTabLayout: TabLayout = binding.tabLayout2
        mViewPager.adapter = VPAdapter(this, args.product.reviews, args.product)
        TabLayoutMediator(mTabLayout, mViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Product Info"
                1 -> tab.text = "Comments"
            }
        }.attach()
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Geri tuşuna basıldığında ListFragment'e geri dön
                findNavController().navigateUp()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.infoText.text = args.product.description
        imageSliderAdapter = ImageSliderAdapter(args.product.images, requireContext())
        binding.viewPager2.adapter = imageSliderAdapter

        checkIfFavoriteAndSetState()
        updateFavoriteProduct()

        TabLayoutMediator(binding.tabLayout3, binding.viewPager2) { tab, position ->
                // Bizim ImageSliderimizin adaptör bağlantısı.Bu noktada herhangi bir özel işlem yapmaya gerek yok
            }.attach()

        /*Glide.with(this) //Image,recycler itemda olduğu için bu contexti veririz.Bu kullanımı tek bir fotoğrafı almak için yaptım.

                .load(args.product.images[0])

                .override(500, 500)

                .error(R.drawable.ic_launcher_background)

                .into(binding.imageView4)
        }*/

        }
    fun checkIfFavoriteAndSetState() {
        val docRef = db.collection("Favorites").whereEqualTo("id", args.product.id)
        docRef.get().addOnSuccessListener { documents ->
            if (_binding != null) {  // binding null olmadığından emin ol
                if (!documents.isEmpty) {
                    binding?.checkBox2?.isChecked = true
                } else {
                    binding?.checkBox2?.isChecked = false
                }
            }
        }.addOnFailureListener { e ->
            Log.e("FirestoreError", "Favori kontrolü başarısız: ", e)
        }
    }

    fun updateFavoriteProduct() {
        binding?.checkBox2?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                val productId = hashMapOf("id" to args.product.id)
                val documentId = db.collection("Favorites").document().id
                db.collection("Favorites").document(documentId)
                    .set(productId)
                    .addOnSuccessListener {
                        if (_binding != null) {
                            binding?.checkBox2?.setBackgroundResource(R.drawable.baseline_star_24)
                        }
                    }
            } else {
                val docRef = db.collection("Favorites").whereEqualTo("id", args.product.id)
                docRef.get().addOnSuccessListener { documents ->
                    if (_binding != null) {
                        for (document in documents) {
                            db.collection("Favorites").document(document.id).delete().addOnSuccessListener {
                                if (_binding != null) {
                                    binding?.checkBox2?.setBackgroundResource(R.drawable.baseline_star_empty)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }

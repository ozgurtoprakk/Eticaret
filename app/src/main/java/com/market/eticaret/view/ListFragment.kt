package com.market.eticaret.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.market.eticaret.R
import com.market.eticaret.adapter.ListAdapter
import com.market.eticaret.databinding.FragmentListBinding
import com.market.eticaret.viewmodel.ListViewModel

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    lateinit var listViewModel:ListViewModel
    var adapter:ListAdapter= ListAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)  // Menü için izin ver
    }
        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.toolbar_menu,menu)//Bu kısımda özel menumuzu fragmentteki toolbarımıza koyduk.
            super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logOut -> {
                // AlertDialog oluşturma
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Log Out")
                builder.setMessage("Are you sure?")

                builder.setPositiveButton("Yes") { dialog, _ ->
                    // Kullanıcı "Evet" dedi, çıkış işlemini yap
                    val sharedPref = activity?.getSharedPreferences("UserInform", MODE_PRIVATE)
                    sharedPref?.edit()?.putBoolean("isLoggedIn",false)?.apply()

                    val action = ListFragmentDirections.actionListFragmentToLoginFragment()
                    view?.let { Navigation.findNavController(it).navigate(action) }

                    dialog.dismiss()
                }

                builder.setNegativeButton("No") { dialog, _ ->
                    // Kullanıcı "Hayır" dedi, dialogu kapat
                    dialog.dismiss()
                }

                // Dialog'u göster
                builder.create().show()

                true
            }
            R.id.favorites->{
                val action=ListFragmentDirections.actionListFragmentToFavoryListFragment()
                view?.let { Navigation.findNavController(it).navigate(action) }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar3)//Toolbarı action bar olarak tanımladık artık butonlu vs. menumuzu ekleyebiliriz.

        listViewModel=ViewModelProvider(this)[ListViewModel::class.java]
        listViewModel.bringProduct()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter=adapter




        observeLiveData()
    }

    fun observeLiveData() {
        //Live data olarak belirlediğimiz products'ta herhangi bir değişikli olduğunda bu kod blaoğu tetiklenir.
        listViewModel.products.observe(viewLifecycleOwner) {
            binding.recyclerView.visibility = View.VISIBLE
            adapter.productListesiniGuncelle(it)

        }}

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
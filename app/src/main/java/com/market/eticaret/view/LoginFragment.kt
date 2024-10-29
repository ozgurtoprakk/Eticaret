package com.market.eticaret.view

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.market.eticaret.R
import com.market.eticaret.databinding.FragmentLoginBinding
import com.market.eticaret.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref2 = activity?.getSharedPreferences("UserInform",MODE_PRIVATE)
        if (sharedPref2 != null) {
            if(sharedPref2.getBoolean("isLoggedIn",true)){
                val action=LoginFragmentDirections.actionLoginFragmentToListFragment()
                view?.let { Navigation.findNavController(it).navigate(action) }
            }
        }
        binding.loginButton.setOnClickListener { loginButton(it) }
        binding.usernameText.setText("jamesd")
        binding.passwordText.setText("jamesdpass")

    }

    fun loginButton(view: View){
        val username=binding.usernameText.text.toString()
        val password=binding.passwordText.text.toString()


        loginViewModel.login(username, password) { personResponse ->
            if (personResponse?.token != null) {

                val sharedPref = activity?.getSharedPreferences("UserInform",MODE_PRIVATE)
                val editor= sharedPref?.edit()//Giriş Bilgilerini kaydediyoruz.
                editor?.putString("username", username) // Kullanıcı adını kaydedin
                editor?.putString("password", password) // Şifreyi kaydedin
                editor?.putBoolean("isLoggedIn", true) // Giriş durumu
                editor?.apply()

                val action = LoginFragmentDirections.actionLoginFragmentToListFragment()
                Navigation.findNavController(view).navigate(action)
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_LONG).show()
            }
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
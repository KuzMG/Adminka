package com.example.adminka.ui.sign_in

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.adminka.R
import com.example.adminka.databinding.FragmentLoginBinding
import com.example.adminka.ui.list_of_tables.ListOfTablesFragment

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.loadLiveData.observe(viewLifecycleOwner){state ->
            when(state){
                LoginViewModel.State.START -> {
                    visibleProgressBar(false)
                }
                LoginViewModel.State.ERROR -> {
                    visibleProgressBar(false)
                    Toast.makeText(requireContext(),"ERROR",Toast.LENGTH_SHORT).show()
                }
                LoginViewModel.State.LOADING -> {
                    visibleProgressBar(true)
                }
                LoginViewModel.State.OK -> {
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container_view,ListOfTablesFragment())
                        .commit()
                }
            }
        }
    }
    fun visibleProgressBar(flag: Boolean){
        binding.run {
            progressBar.isVisible = flag
            passwordInputLayout.isVisible = !flag
            loginInputLayout.isVisible = !flag
            button.isVisible = !flag
        }
    }

    override fun onStart() {
        super.onStart()
        binding.button.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.signIn(login,password)
        }
    }

}
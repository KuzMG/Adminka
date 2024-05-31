package com.example.adminka.ui.add_row

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.adminka.R
import com.example.adminka.data.Client
import com.example.adminka.data.Employee
import com.example.adminka.databinding.FragmentAddClientBinding
import com.example.adminka.databinding.FragmentAddEmployeeBinding
import com.example.adminka.ui.table.TableViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class AddClientFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddClientBinding
    private lateinit var viewModel: TableViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[TableViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddClientBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.button.setOnClickListener {
            val client = Client(
                0,
                binding.fullNameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.phoneEditText.text.toString()
            )
            viewModel.addClient(client)
            dismiss()
        }
    }

}
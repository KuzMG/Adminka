package com.example.adminka.ui.add_role

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.adminka.data.Check
import com.example.adminka.databinding.FragmentAddCheckBinding
import com.example.adminka.databinding.FragmentAddRoleBinding
import com.example.adminka.ui.table.TableViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Date


class AddRoleFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddRoleBinding
    private lateinit var viewModel: TableViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[TableViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRoleBinding.inflate(inflater,container,false)
        binding.typeSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,
            listOf("manager","admin","analyst"))
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.button.setOnClickListener {
            viewModel.addRole(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.typeSpinner.selectedItem.toString(),
                binding.idEmployeeEditText.text.toString().toInt()
                )
            dismiss()
        }
    }

}
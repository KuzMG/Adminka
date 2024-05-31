package com.example.adminka.ui.add_row

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.adminka.R
import com.example.adminka.data.Employee
import com.example.adminka.data.Room
import com.example.adminka.databinding.FragmentAddEmployeeBinding
import com.example.adminka.databinding.FragmentAddRoomBinding
import com.example.adminka.ui.table.TableViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class AddRoomFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddRoomBinding
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
        binding = FragmentAddRoomBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.button.setOnClickListener {
            val room = Room(
                0,
                binding.addressEditText.text.toString(),
                binding.postcodeEditText.text.toString().toInt(),
                binding.phoneNumberEditText.text.toString(),
                binding.numberOfEmployeeEditText.text.toString().toInt(),
                binding.typeEditText.text.toString()
            )
            viewModel.addRoom(room)
            dismiss()
        }
    }

}
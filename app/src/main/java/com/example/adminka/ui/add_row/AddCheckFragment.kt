package com.example.adminka.ui.add_row

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.adminka.R
import com.example.adminka.data.Check
import com.example.adminka.data.Employee
import com.example.adminka.databinding.FragmentAddCheckBinding
import com.example.adminka.databinding.FragmentAddEmployeeBinding
import com.example.adminka.ui.table.TableViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class AddCheckFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddCheckBinding
    private lateinit var viewModel: TableViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[TableViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCheckBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.button.setOnClickListener {
            val check = Check(
                0,
                binding.idClientEditText.text.toString(),
                binding.priceEditText.text.toString(),
                Date().toString(),
                binding.idServerEditText.text.toString()
            )
            viewModel.addCheck(check)
            dismiss()
        }
    }

}
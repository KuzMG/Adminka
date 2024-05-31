package com.example.adminka.ui.table

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.adapter.CPUAdapter
import com.example.adminka.adapter.CheckAdapter
import com.example.adminka.adapter.ClientAdapter
import com.example.adminka.adapter.DriveAdapter
import com.example.adminka.adapter.EmployeeAdapter
import com.example.adminka.adapter.RamAdapter
import com.example.adminka.adapter.RoomAdapter
import com.example.adminka.adapter.ServerAdapter
import com.example.adminka.adapter.TaskAdapter
import com.example.adminka.data.CPU
import com.example.adminka.data.Check
import com.example.adminka.data.Client
import com.example.adminka.data.Drive
import com.example.adminka.data.Employee
import com.example.adminka.data.RAM
import com.example.adminka.data.Room
import com.example.adminka.data.Server
import com.example.adminka.data.Task
import com.example.adminka.databinding.FragmentTableBinding
import com.example.adminka.ui.add_row.AddCPUFragment
import com.example.adminka.ui.add_row.AddCheckFragment
import com.example.adminka.ui.add_row.AddClientFragment
import com.example.adminka.ui.add_row.AddDriveFragment
import com.example.adminka.ui.add_row.AddEmployeeFragment
import com.example.adminka.ui.add_row.AddRAMFragment
import com.example.adminka.ui.add_row.AddRoomFragment
import com.example.adminka.ui.add_row.AddServerFragment
import com.example.adminka.ui.add_row.AddTaskFragment
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import kotlin.coroutines.coroutineContext

class TableFragment : Fragment() {
    private lateinit var viewModel: TableViewModel
    private lateinit var binding: FragmentTableBinding

    companion object {
        const val KEY_TABLE = "table"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TableViewModel::class.java)
        viewModel.table = requireArguments().getString(KEY_TABLE, "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTableBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTable().enqueue(object : com.squareup.okhttp.Callback {
            override fun onFailure(request: Request, e: IOException?) {

            }

            override fun onResponse(response: Response) {
                val body = response.body().string()
                Handler(Looper.getMainLooper()).post {
                    binding.recyclerView.adapter = when (viewModel.table) {
                        "employee" -> EmployeeAdapter(Employee.fromResponse(body),viewModel)
                        "room" -> RoomAdapter(Room.fromResponse(body),viewModel)
                        "task" -> TaskAdapter(Task.fromResponse(body),viewModel)
                        "server" -> ServerAdapter(Server.fromResponse(body),viewModel)
                        "cpu" -> CPUAdapter(CPU.fromResponse(body),viewModel)
                        "ram" -> RamAdapter(RAM.fromResponse(body),viewModel)
                        "drive" -> DriveAdapter(Drive.fromResponse(body),viewModel)
                        "client" -> ClientAdapter(Client.fromResponse(body),viewModel)
                        "check" -> CheckAdapter(Check.fromResponse(body),viewModel)
                        else -> throw IndexOutOfBoundsException()

                    }
                }

            }

        })
    }

    override fun onStart() {
        super.onStart()
        binding.addButton.setOnClickListener {
            when (viewModel.table) {
                "employee" -> AddEmployeeFragment().show(childFragmentManager,null)
                "room" -> AddRoomFragment().show(childFragmentManager,null)
                "task" -> AddTaskFragment().show(childFragmentManager,null)
                "server" ->AddServerFragment().show(childFragmentManager,null)
                "cpu" -> AddCPUFragment().show(childFragmentManager,null)
                "ram" -> AddRAMFragment().show(childFragmentManager,null)
                "drive" -> AddDriveFragment().show(childFragmentManager,null)
                "client" ->AddClientFragment().show(childFragmentManager,null)
                "check" -> AddCheckFragment().show(childFragmentManager,null)
                else -> throw IndexOutOfBoundsException()

            }
        }
    }


}
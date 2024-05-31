package com.example.adminka.ui.list_of_tables

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.ui.add_role.AddRoleFragment
import com.example.adminka.ui.table.TableFragment

class ListOfTablesFragment : Fragment() {


    private lateinit var viewModel: ListOfTablesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    private val list =
        listOf("employee", "room", "task", "server", "cpu", "ram", "drive", "client", "check")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_tables, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        button = view.findViewById(R.id.add_role_button)
        recyclerView.adapter = Adapter(list)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListOfTablesViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        button.setOnClickListener {
            AddRoleFragment().show(childFragmentManager, null)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container_view, TableFragment::class.java,
                        Bundle().apply {
                            putString(
                                TableFragment.KEY_TABLE,
                                (it as TextView).text.toString()
                            )
                        })
                    .addToBackStack(null)
                    .commit()

            }
        }

        fun onBind(text: String) {
            itemView.findViewById<TextView>(android.R.id.text1).setText(text)
        }
    }

    inner class Adapter(private val list: List<String>) : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount() = list.size
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.onBind(list[position])
        }


    }
}
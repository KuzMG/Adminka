package com.example.adminka.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.data.Employee
import com.example.adminka.data.Server
import com.example.adminka.databinding.ItemEmployeeBinding
import com.example.adminka.databinding.ItemServerBinding
import com.example.adminka.ui.table.TableFragment
import com.example.adminka.ui.table.TableViewModel

class ServerAdapter(val list: MutableList<Server>, val viewModel: TableViewModel): RecyclerView.Adapter<ServerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_server,parent,false)
        return ServerViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        holder.onBind(list[position])
        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list[holder.adapterPosition].run {
                    name = holder.binding.nameEditView.text.toString()
                    price = holder.binding.priceEditView.text.toString().toInt()
                    id_ram = holder.binding.idRamEditView.text.toString()
                    id_drive = holder.binding.idDriveEditView.text.toString()
                    id_cpu = holder.binding.idCpuEditView.text.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        holder.binding.run {
            updateButton.setOnClickListener {
                viewModel.updateServer(list[position])
            }
            deleteButton.setOnClickListener {
                viewModel.deleteServer(list[position])
                list.removeAt(position)
                notifyDataSetChanged()
            }
            nameEditView.addTextChangedListener(textWatcher)
            priceEditView.addTextChangedListener(textWatcher)
            idRamEditView.addTextChangedListener(textWatcher)
            idDriveEditView.addTextChangedListener(textWatcher)
            idCpuEditView.addTextChangedListener(textWatcher)
        }
    }


}
class ServerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var item: Server
    val binding: ItemServerBinding = ItemServerBinding.bind(itemView)
    fun onBind(item: Server){
        this.item= item
        binding.run {
            idEditView.setText(item.id.toString())
            nameEditView.setText(item.name)
            priceEditView.setText(item.price.toString())
            idCpuEditView.setText(item.id_cpu)
            idRamEditView.setText(item.id_ram)
            idDriveEditView.setText(item.id_drive)
        }
    }
}


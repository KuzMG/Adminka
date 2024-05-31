package com.example.adminka.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.data.Client
import com.example.adminka.data.Employee
import com.example.adminka.databinding.ItemClientBinding
import com.example.adminka.databinding.ItemEmployeeBinding
import com.example.adminka.ui.table.TableFragment
import com.example.adminka.ui.table.TableViewModel

class ClientAdapter(val list: MutableList<Client>, val viewModel: TableViewModel): RecyclerView.Adapter<ClientViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_client,parent,false)
        return ClientViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.onBind(list[position])

        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list[holder.adapterPosition].run {
                    phone = holder.binding.phoneEditView.text.toString()
                    email = holder.binding.emailEditView.text.toString()
                    fullName =holder.binding.fullNameEditView.text.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        holder.binding.run {
            updateButton.setOnClickListener {
                viewModel.updateClient(list[position])
            }
            deleteButton.setOnClickListener {
                viewModel.deleteClient(list[position])
                list.removeAt(position)
                notifyDataSetChanged()
            }
            phoneEditView.addTextChangedListener(textWatcher)
            fullNameEditView.addTextChangedListener(textWatcher)
            emailEditView.addTextChangedListener(textWatcher)
        }
    }


}
class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var item: Client
    val binding: ItemClientBinding = ItemClientBinding.bind(itemView)
    fun onBind(item: Client){
        this.item= item
        binding.run {
            idEditView.setText(item.id.toString())
            fullNameEditView.setText(item.fullName)
            emailEditView.setText(item.email)
            phoneEditView.setText(item.phone)
        }
    }
}


package com.example.adminka.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.data.Check
import com.example.adminka.data.Client
import com.example.adminka.data.Employee
import com.example.adminka.databinding.ItemCheckBinding
import com.example.adminka.databinding.ItemClientBinding
import com.example.adminka.databinding.ItemEmployeeBinding
import com.example.adminka.ui.table.TableFragment
import com.example.adminka.ui.table.TableViewModel

class CheckAdapter(val list: MutableList<Check>,val viewModel: TableViewModel): RecyclerView.Adapter<CheckViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_check,parent,false)
        return CheckViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: CheckViewHolder, position: Int) {
        holder.onBind(list[position])
        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list[position].run {
                    date = holder.binding.dateEditView.text.toString()
                    price = holder.binding.priceEditView.text.toString()
                    idClient =holder.binding.idClientEditView.text.toString()
                    idServer = holder.binding.idServerEditView.text.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        holder.binding.run {
            updateButton.setOnClickListener {
                viewModel.updateCheck(list[position])
            }
            deleteButton.setOnClickListener {
                viewModel.deleteCheck(list[position])
                list.removeAt(position)
                notifyDataSetChanged()
            }
            dateEditView.addTextChangedListener(textWatcher)
            priceEditView.addTextChangedListener(textWatcher)
            idClientEditView.addTextChangedListener(textWatcher)
            idServerEditView.addTextChangedListener(textWatcher)
        }
    }


}
class CheckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var item: Check
    val binding: ItemCheckBinding = ItemCheckBinding.bind(itemView)
    fun onBind(item: Check){
        this.item= item
        binding.run {
            idEditView.setText(item.id.toString())
            idClientEditView.setText(item.idClient)
            priceEditView.setText(item.price)
            dateEditView.setText(item.date)
            idServerEditView.setText(item.idServer)
        }
    }
}


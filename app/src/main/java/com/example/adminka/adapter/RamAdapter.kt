package com.example.adminka.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.data.RAM
import com.example.adminka.databinding.ItemRamBinding
import com.example.adminka.databinding.ItemServerBinding
import com.example.adminka.ui.table.TableFragment
import com.example.adminka.ui.table.TableViewModel

class RamAdapter(val list: MutableList<RAM>, val viewModel: TableViewModel): RecyclerView.Adapter<RamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ram,parent,false)
        return RamViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: RamViewHolder, position: Int) {
        holder.onBind(list[position])
        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list[holder.adapterPosition].run {
                    type = holder.binding.typeEditView.text.toString()
                    memory = holder.binding.memoryEditView.text.toString().toInt()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        holder.binding.run {
            updateButton.setOnClickListener {
                viewModel.updateRam(list[position])
            }
            deleteButton.setOnClickListener {
                viewModel.deleteRam(list[position])
                list.removeAt(position)
                notifyDataSetChanged()
            }
            typeEditView.addTextChangedListener(textWatcher)
            memoryEditView.addTextChangedListener(textWatcher)
        }
    }


}
class RamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var item: RAM
    val binding: ItemRamBinding = ItemRamBinding.bind(itemView)
    fun onBind(item: RAM){
        this.item= item
        binding.run {
            idEditView.setText(item.id.toString())
            typeEditView.setText(item.type)
            memoryEditView.setText(item.memory.toString())
        }
    }
}


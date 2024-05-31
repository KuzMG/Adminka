package com.example.adminka.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.data.CPU
import com.example.adminka.data.Employee
import com.example.adminka.databinding.ItemCpuBinding
import com.example.adminka.databinding.ItemEmployeeBinding
import com.example.adminka.ui.table.TableFragment
import com.example.adminka.ui.table.TableViewModel

class CPUAdapter(val list: MutableList<CPU>, val viewModel: TableViewModel): RecyclerView.Adapter<CPUViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CPUViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee,parent,false)
        return CPUViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: CPUViewHolder, position: Int) {
        holder.onBind(list[position])
        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list[holder.adapterPosition].run {
                    cores = holder.binding.coresEditView.text.toString().toInt()
                    threads = holder.binding.threadsEditView.text.toString().toInt()
                    model =holder.binding.modelEditView.text.toString()
                    frequency = holder.binding.frequencyEditView.text.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        holder.binding.run {
            updateButton.setOnClickListener {
                viewModel.updateCPU(list[position])
            }
            deleteButton.setOnClickListener {
                viewModel.deleteCPU(list[position])
                list.removeAt(position)
                notifyDataSetChanged()
            }
            coresEditView.addTextChangedListener(textWatcher)
            threadsEditView.addTextChangedListener(textWatcher)
            modelEditView.addTextChangedListener(textWatcher)
            frequencyEditView.addTextChangedListener(textWatcher)
        }
    }


}
class CPUViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var item: CPU
    val binding: ItemCpuBinding = ItemCpuBinding.bind(itemView)
    fun onBind(item: CPU){
        this.item= item
        binding.run {
            idEditView.setText(item.id.toString())
            modelEditView.setText(item.model)
            coresEditView.setText(item.cores)
            threadsEditView.setText(item.threads)
            frequencyEditView.setText(item.frequency.toString())
        }
    }
}


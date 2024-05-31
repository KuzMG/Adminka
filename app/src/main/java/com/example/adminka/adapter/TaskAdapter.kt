package com.example.adminka.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.data.Task
import com.example.adminka.databinding.ItemTaskBinding
import com.example.adminka.ui.table.TableFragment
import com.example.adminka.ui.table.TableViewModel

class TaskAdapter(val list: MutableList<Task>, val viewModel: TableViewModel): RecyclerView.Adapter<TaskViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return TaskViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list[holder.adapterPosition].run {
                    task = holder.binding.taskEditView.text.toString()
                    active = holder.binding.activeEditView.text.toString().toBoolean()
                    idEmployee = holder.binding.idEmployeeEditView.text.toString().toInt()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        holder.binding.run {
            updateButton.setOnClickListener {
                viewModel.updateTask(list[position])
            }
            deleteButton.setOnClickListener {
                viewModel.deleteTask(list[position])
                list.removeAt(position)
                notifyDataSetChanged()
            }
            taskEditView.addTextChangedListener(textWatcher)
            activeEditView.addTextChangedListener(textWatcher)
            idEmployeeEditView.addTextChangedListener(textWatcher)
        }
    }


}
class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var item: Task
    val binding: ItemTaskBinding = ItemTaskBinding.bind(itemView)
    fun onBind(item: Task){
        this.item= item
        binding.run {
            idEditView.setText(item.id.toString())
            taskEditView.setText(item.task)
            activeEditView.setText(item.active.toString())
            idEmployeeEditView.setText(item.idEmployee.toString())
        }
    }
}
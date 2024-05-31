package com.example.adminka.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.data.Employee
import com.example.adminka.databinding.ItemEmployeeBinding
import com.example.adminka.ui.table.TableFragment
import com.example.adminka.ui.table.TableViewModel

class EmployeeAdapter(val list: MutableList<Employee>,val viewModel: TableViewModel): RecyclerView.Adapter<EmployeeViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee,parent,false)
        return EmployeeViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.onBind(list[position])
        val textWatcher = object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list[holder.adapterPosition].run {
                    age = holder.binding.ageEditView.text.toString().toInt()
                    jobTitle =holder.binding.jobTitleEditView.text.toString()
                    salary =holder.binding.salaryEditView.text.toString().toInt()
                    phoneNumber = holder.binding.phoneNumberEditView.text.toString()
                    experience = holder.binding.experienceEditView.text.toString().toInt()
                    idRoom = holder.binding.idRoomEditView.text.toString().toInt()
                    email = holder.binding.idRoomEditView.text.toString()
                    fullName= holder.binding.fullNameEditView.text.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        holder.binding.run {
            updateButton.setOnClickListener {
                viewModel.updateEmployee(list[position])
            }
            deleteButton.setOnClickListener {
                viewModel.deleteEmployee(list[position])
                list.removeAt(position)
                notifyDataSetChanged()
            }
            idEditView.addTextChangedListener(textWatcher)
            fullNameEditView.addTextChangedListener(textWatcher)
            jobTitleEditView.addTextChangedListener(textWatcher)
            phoneNumberEditView.addTextChangedListener(textWatcher)
            experienceEditView.addTextChangedListener(textWatcher)
            emailEditView.addTextChangedListener(textWatcher)
            salaryEditView.addTextChangedListener(textWatcher)
            idRoomEditView.addTextChangedListener(textWatcher)
            ageEditView.addTextChangedListener(textWatcher)
        }


    }


}
class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var item: Employee
    val binding: ItemEmployeeBinding = ItemEmployeeBinding.bind(itemView)
    fun onBind(item: Employee){
        this.item= item


        binding.run {
            idEditView.setText(item.id.toString())
            fullNameEditView.setText(item.fullName)
            jobTitleEditView.setText(item.jobTitle)
            phoneNumberEditView.setText(item.phoneNumber)
            experienceEditView.setText(item.experience.toString())
            emailEditView.setText(item.email)
            salaryEditView.setText(item.salary.toString())
            idRoomEditView.setText(item.idRoom.toString())
            ageEditView.setText(item.age.toString())

        }
    }
}


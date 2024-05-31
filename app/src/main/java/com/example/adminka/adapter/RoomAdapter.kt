package com.example.adminka.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminka.R
import com.example.adminka.data.Room
import com.example.adminka.databinding.ItemRoomBinding
import com.example.adminka.ui.table.TableFragment
import com.example.adminka.ui.table.TableViewModel

class RoomAdapter(val list: MutableList<Room>, val viewModel: TableViewModel): RecyclerView.Adapter<RoomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room,parent,false)
        return RoomViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.onBind(list[position])
        val textWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                list[holder.adapterPosition].run {
                    type = holder.binding.typeEditView.text.toString()
                    address = holder.binding.addressEditView.text.toString()
                    postcode = holder.binding.postcodeEditView.text.toString().toInt()
                    numberOfEmployee = holder.binding.numberOfEmployeeEditView.text.toString().toInt()
                    phoneNumber = holder.binding.phoneNumberEditView.text.toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        }
        holder.binding.run {
            updateButton.setOnClickListener {
                viewModel.updateRoom(list[position])
            }
            deleteButton.setOnClickListener {
                viewModel.deleteRoom(list[position])
                list.removeAt(position)
                notifyDataSetChanged()
            }
            typeEditView.addTextChangedListener(textWatcher)
            addressEditView.addTextChangedListener(textWatcher)
            postcodeEditView.addTextChangedListener(textWatcher)
            numberOfEmployeeEditView.addTextChangedListener(textWatcher)
            phoneNumberEditView.addTextChangedListener(textWatcher)
        }
    }


}
class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    lateinit var item: Room
    val binding: ItemRoomBinding = ItemRoomBinding.bind(itemView)
    fun onBind(item: Room){
        this.item= item
        binding.run {
            idEditView.setText(item.id.toString())
            addressEditView.setText(item.address)
            postcodeEditView.setText(item.postcode.toString())
            phoneNumberEditView.setText(item.phoneNumber)
            numberOfEmployeeEditView.setText(item.numberOfEmployee.toString())
            typeEditView.setText(item.type)
        }
    }
}

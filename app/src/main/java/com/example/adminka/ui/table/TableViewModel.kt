package com.example.adminka.ui.table

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminka.data.CPU
import com.example.adminka.data.Check
import com.example.adminka.data.Client
import com.example.adminka.data.Drive
import com.example.adminka.data.Employee
import com.example.adminka.data.RAM
import com.example.adminka.data.Room
import com.example.adminka.data.Server
import com.example.adminka.data.Task
import com.example.adminka.network.Manager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TableViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var table: String
    val manager = Manager(application)
    fun getTable() = manager.getTable(table)
    fun addCPU(cpu: CPU) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.insertCPU(cpu).execute()
        }

    }

    fun addDrive(drive: Drive) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.insertDrive(drive).execute()
        }
    }

    fun addRam(ram: RAM) {
        viewModelScope.launch(Dispatchers.IO) {

            manager.insertRam(ram).execute()

        }

    }

    fun addServer(server: Server) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.insertServer(server).execute()
        }

    }

    fun addClient(client: Client) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.insertClient(client).execute()
        }
    }

    fun addCheck(check: Check) {
        viewModelScope.launch(Dispatchers.IO) {

            manager.insertCheck(check).execute()
        }
    }

    fun addRole(login: String, password: String,type: String, idEmployee: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.addRole(login,password,type,idEmployee).execute()
        }
    }

    fun addEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.insertEmployee(employee).execute()
        }
    }

    fun addRoom(room: Room) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.insertRoom(room).execute()
        }

    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.insertTask(task).execute()
        }
    }

    fun updateCPU(cpu: CPU) {

        viewModelScope.launch(Dispatchers.IO) {
            manager.updateTable("cpu", cpu.id.toString(), "model", cpu.model).execute()
            manager.updateTable("cpu", cpu.id.toString(), "threads", cpu.threads).execute()
            manager.updateTable("cpu", cpu.id.toString(), "cores", cpu.cores).execute()
            manager.updateTable("cpu", cpu.id.toString(), "frequency", cpu.frequency).execute()
        }

    }

    fun updateDrive(drive: Drive) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateTable("drive", drive.id.toString(), "memory", drive.memory).execute()
            manager.updateTable("drive", drive.id.toString(), "type", drive.type).execute()
        }
    }

    fun updateRam(ram: RAM) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateTable("ram", ram.id.toString(), "memory", ram.memory).execute()
            manager.updateTable("ram", ram.id.toString(), "type", ram.type).execute()
        }

    }

    fun updateServer(server: Server) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateTable("server", server.id.toString(), "name", server.name).execute()
            manager.updateTable("server", server.id.toString(), "price", server.price).execute()
            manager.updateTable("server", server.id.toString(), "id_ram", server.id_ram).execute()
            manager.updateTable("server", server.id.toString(), "id_drive", server.id_drive)
                .execute()
            manager.updateTable("server", server.id.toString(), "id_cpu", server.id_cpu).execute()
        }

    }

    fun updateClient(client: Client) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateTable("client", client.id.toString(), "email", client.email).execute()
            manager.updateTable("client", client.id.toString(), "phone", client.phone).execute()
            manager.updateTable("client", client.id.toString(), "full_name", client.fullName)
                .execute()
        }
    }

    fun updateCheck(check: Check) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateTable("check", check.id.toString(), "date", check.date).execute()
            manager.updateTable("check", check.id.toString(), "price", check.price).execute()
            manager.updateTable("check", check.id.toString(), "id_server", check.idServer).execute()
            manager.updateTable("check", check.id.toString(), "id_client", check.idClient).execute()
        }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateTable("employee", employee.id.toString(), "age", employee.age).execute()
            manager.updateTable("employee", employee.id.toString(), "full_name", employee.fullName)
                .execute()
            manager.updateTable("employee", employee.id.toString(), "id_room", employee.idRoom)
                .execute()
            manager.updateTable("employee", employee.id.toString(), "salary", employee.salary)
                .execute()
            manager.updateTable(
                "employee",
                employee.id.toString(),
                "experience",
                employee.experience
            ).execute()
            manager.updateTable(
                "employee",
                employee.id.toString(),
                "phone_number",
                employee.phoneNumber
            ).execute()
            manager.updateTable("employee", employee.id.toString(), "job_title", employee.jobTitle)
                .execute()
            manager.updateTable("employee", employee.id.toString(), "email", employee.email)
                .execute()
        }
    }

    fun updateRoom(room: Room) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateTable("room", room.id.toString(), "type", room.type).execute()
            manager.updateTable("room", room.id.toString(), "phone_number", room.phoneNumber)
                .execute()
            manager.updateTable("room", room.id.toString(), "address", room.address).execute()
            manager.updateTable(
                "room",
                room.id.toString(),
                "number_of_employee",
                room.numberOfEmployee
            ).execute()
            manager.updateTable("room", room.id.toString(), "postcode", room.postcode).execute()
        }

    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateTable("task", task.id.toString(), "task", task.task).execute()
            manager.updateTable("task", task.id.toString(), "active", task.active).execute()
            manager.updateTable("task", task.id.toString(), "id_employee", task.idEmployee)
                .execute()
        }
    }


    fun deleteCPU(cpu: CPU) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.delTable("cpu", cpu.id.toString()).execute()
        }

    }

    fun deleteDrive(drive: Drive) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.delTable("drive", drive.id.toString()).execute()
        }
    }

    fun deleteRam(ram: RAM) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.delTable("ram", ram.id.toString()).execute()
        }

    }

    fun deleteServer(server: Server) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.delTable("server", server.id.toString()).execute()
        }

    }

    fun deleteClient(client: Client) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.delTable("client", client.id.toString()).execute()
        }
    }

    fun deleteCheck(check: Check) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.delTable("check", check.id.toString()).execute()
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.delTable("employee", employee.id.toString()).execute()
        }
    }

    fun deleteRoom(room: Room) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.delTable("room", room.id.toString()).execute()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.delTable("task", task.id.toString()).execute()
        }
    }
}
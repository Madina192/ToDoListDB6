package com.example.todolistdb6.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todolistdb6.App
import com.example.todolistdb6.R
import com.example.todolistdb6.Task
import com.example.todolistdb6.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {
    private lateinit var binding : FragmentTaskBinding
    private var task: Task? = null
    private lateinit var data: Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun buttonHandleClick() {
        binding.btnSave.setOnClickListener {
            data = Task(
                title = binding.etTitle.text.toString()
            )
            if(binding.etTitle.text.toString().isNotEmpty()) {
                if (task != null) {
                    updateTask()
                } else {
                    saveTask()
                }
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), getString(R.string.you_can_t_save_an_empty_task), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveTask() {
        task = Task(data.id, data.title, data.isDone)
        App.db.taskDao().insert(task!!)
    }

    private fun updateTask() {
        task!!.title = data.title
        task!!.isDone = data.isDone
        App.db.taskDao().update(task!!)
    }

}
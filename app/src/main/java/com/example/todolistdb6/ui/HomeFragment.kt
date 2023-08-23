package com.example.todolistdb6.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todolistdb6.MainActivity
import com.example.todolistdb6.MainViewModel
import com.example.todolistdb6.R
import com.example.todolistdb6.Task
import com.example.todolistdb6.TaskAdapter
import com.example.todolistdb6.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    var menu_list = arrayOf("All", "Completed", "Uncompleted")
    private lateinit var adapterItems : ArrayAdapter<String>
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterItems = ArrayAdapter(requireContext(), R.layout.list_item)
        binding.autoCompleteTextView.setAdapter(adapterItems)
        initClickers()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        adapter = TaskAdapter(this::onClick, this::onLongClick, this::onBtnClick)
        binding.recyclerView.adapter = adapter
    }

    private fun initClickers() {
        binding.autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val item = binding.autoCompleteTextView.text
            binding.tvTitle.text = item
        }
        binding.btnTask.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun onClick(task: Task) {
        viewModel.markTaskAsDone(task)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onLongClick(task: Task) {
        val alertDialogDelete = AlertDialog.Builder(requireContext())
        alertDialogDelete.setMessage(getString(R.string.delete_message))

        alertDialogDelete.setPositiveButton(getString(R.string.yes)) { _, _ ->
            adapter.notifyDataSetChanged()
        }

        alertDialogDelete.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog?.cancel()
        }

        alertDialogDelete.create().show()
    }

    private fun onBtnClick(task: Task) {

    }
}
package com.example.todolistdb6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistdb6.databinding.ItemTaskBinding

class TaskAdapter(
    private val onClick: (Task) -> Unit,
    private val onLongClick: (Task) -> Unit,
    private val onBtnClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    val list = listOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(task: Task) {
            binding.checkBox.isChecked = task.isDone == true
            binding.tvTitle.text = task.title
            itemView.setOnClickListener {
                onClick(task)
            }
            itemView.setOnLongClickListener {
                onLongClick(task)
                true
            }
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                task.isDone = isChecked
            }
            binding.ivEdit.setOnClickListener{
                onBtnClick(task)
            }
        }
    }
}
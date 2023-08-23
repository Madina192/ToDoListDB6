package com.example.todolistdb6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){
    private val filterType = MutableLiveData(String)

    fun markTaskAsDone(task: Task) {
        task.isDone = true
    }

}
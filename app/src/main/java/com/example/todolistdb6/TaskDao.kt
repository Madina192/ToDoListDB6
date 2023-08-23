package com.example.todolistdb6

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface TaskDao {
    @Query("SELECT * FROM tasks_table")
    fun getAll(): List<Task>

    @Query("SELECT * FROM tasks_table where isDone = 'true'")
    fun getCompletedTasks(): List<Task>

    @Query("SELECT * FROM tasks_table where isDone = 'false'")
    fun getUnCompletedTasks(): List<Task>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}
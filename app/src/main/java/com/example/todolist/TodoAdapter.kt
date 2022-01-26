package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter (
    private val todos: MutableList<Todo>
)
    //define TodoViewHolderClass
    :  RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()

{
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) //for hold the view.

    //Ctrl + i to generate function
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate( //to convert xml to kotlin
                R.layout.item_todo, //layout page name
                parent,
                false // no need to attach the layout to the root

            )
        )
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){ //text view that we want to strike through

        if (isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG //make a strike
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv() //remove strike
        }

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) { //interact with ui
        val curTodo = todos[position]   //widget yg digunakan
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->  //letak _ sbb x guna parameter tu
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size //data from todoList
    }
}
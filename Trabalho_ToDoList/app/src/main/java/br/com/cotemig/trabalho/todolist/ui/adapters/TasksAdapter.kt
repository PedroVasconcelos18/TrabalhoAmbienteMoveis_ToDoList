package br.com.cotemig.trabalho.todolist.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.trabalho.todolist.R
import br.com.cotemig.trabalho.todolist.models.Task

class TasksAdapter (var context: Context, var list: List<Task>, var onClickTask: (Task) -> Unit) :
    RecyclerView.Adapter<TasksAdapter.TaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.TaskHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TasksAdapter.TaskHolder, position: Int) {
        holder.bind(list[position], onClickTask)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TaskHolder(var view: View): RecyclerView.ViewHolder(view) {
        fun bind(task: Task, onClickTask: (Task) -> Unit) {
            var nameTask = view.findViewById<TextView>(R.id.task_name)
            nameTask.setOnClickListener{
                onClickTask(task)
            }

        }
    }

}

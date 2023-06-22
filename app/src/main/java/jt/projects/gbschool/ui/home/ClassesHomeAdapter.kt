package jt.projects.gbschool.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jt.projects.gbschool.model.Lesson
import jt.projects.gbschool.ui.viewholders.LessonHomeViewHolder

class ClassesHomeAdapter(
    private var onItemClicked: ((Lesson) -> Unit)? = null,
    private var onScrollToCurrentPosition: ((Int) -> Unit)? = null,
) : RecyclerView.Adapter<LessonHomeViewHolder>() {

    private var data: List<Lesson> = arrayListOf()

    // Метод передачи данных в адаптер
    fun setData(data: List<Lesson>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LessonHomeViewHolder(parent)

    override fun onBindViewHolder(holder: LessonHomeViewHolder, position: Int) {
        holder.bind(data[position], onItemClicked, onScrollToCurrentPosition)
    }

    override fun getItemCount(): Int = data.size
}
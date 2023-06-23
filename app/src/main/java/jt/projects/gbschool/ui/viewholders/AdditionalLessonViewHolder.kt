package jt.projects.gbschool.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jt.projects.gbschool.R
import jt.projects.gbschool.databinding.ItemLessonAdditionalBinding
import jt.projects.gbschool.model.Lesson
import jt.projects.gbschool.utils.CURRENT_TIME

class AdditionalLessonViewHolder private constructor(
    private val binding: ItemLessonAdditionalBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemLessonAdditionalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(data: Lesson, onItemClicked: ((Lesson) -> Unit)?) {

        if (layoutPosition != RecyclerView.NO_POSITION) {
            with(binding) {

                tvLessonName.text = data.name
                tvTeacher.text = "Teacher: ${data.teacher}"
                tvTime.text = "${data.timeStart} - ${data.timeEnd}"
                tvDescription.text = data.description

                ivImage.load(data.image) {
                    error(R.drawable.baseline_image_not_supported_24)
                }

                if (data.isCurrent) {
                    gLine.bigPoint.isVisible = true
                }
            }
        }
    }
}
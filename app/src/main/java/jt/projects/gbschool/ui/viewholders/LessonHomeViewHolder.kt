package jt.projects.gbschool.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jt.projects.gbschool.R
import jt.projects.gbschool.databinding.ItemLessonAtHomeFragmentBinding
import jt.projects.gbschool.model.Lesson
import jt.projects.gbschool.utils.CURRENT_TIME

class LessonHomeViewHolder private constructor(
    private val binding: ItemLessonAtHomeFragmentBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemLessonAtHomeFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(
        data: Lesson,
        onItemClicked: ((Lesson) -> Unit)?,
        onScrollToCurrentPosition: ((Int) -> Unit)?
    ) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            with(binding) {

                tvLessonName.text = data.name
                tvTime.text = "${data.timeStart} - ${data.timeEnd}"
                ivImage.load(data.image) {
                    error(R.drawable.baseline_image_not_supported_24)
                }

                if (data.isOpenIn) {
                    btnOpenIn.root.apply {
                        isVisible = true
                        setOnClickListener {
                            onItemClicked?.invoke(data)
                        }
                    }
                }

            }
        }
    }
}
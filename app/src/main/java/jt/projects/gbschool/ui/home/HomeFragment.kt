package jt.projects.gbschool.ui.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import jt.projects.gbschool.databinding.FragmentHomeBinding
import jt.projects.gbschool.model.Lesson
import jt.projects.gbschool.ui.classes.ClassesViewModel
import jt.projects.gbschool.utils.CURRENT_TIME
import jt.projects.gbschool.utils.showSnackbar
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClassesViewModel by inject()
    private val classesAdapter by lazy { ClassesHomeAdapter(::onItemClicked) }

    private fun onItemClicked(data: Lesson) {
        try {
            val sky = Intent(Intent.ACTION_VIEW)
            sky.data = Uri.parse("skype:${data.teacher}?call&video=true")
            val chosenIntent = Intent.createChooser(sky, "Выберите программу")
            startActivity(chosenIntent)
        } catch (e: ActivityNotFoundException) {
            showSnackbar(e.message.toString())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeViewModelData()
        observeLoadingVisible()
    }

    private fun initUi() {
        with(binding.homeSection2.rvLessonsList) {
            adapter = classesAdapter
        }
    }

    private fun observeViewModelData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .resultRecycler
                    .collect {
                        binding.homeSection2.tvClassesCount.text = "${it.size} classes today"
                        classesAdapter.setData(it)

                        val curLessonIndex = it.indexOf(it.findLast { lesson ->
                            CURRENT_TIME in lesson.timeStart..lesson.timeEnd
                        })
                        if (curLessonIndex != -1) {
                            binding.homeSection2.rvLessonsList.scrollToPosition(curLessonIndex)
                        }
                    }
            }
        }
    }

    private fun observeLoadingVisible() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect {
                    binding.loadingFrameLayout.root.isVisible = it
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
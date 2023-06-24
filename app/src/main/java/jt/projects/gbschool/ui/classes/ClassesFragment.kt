package jt.projects.gbschool.ui.classes

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
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import dagger.android.support.DaggerFragment
import jt.projects.gbschool.App
import jt.projects.gbschool.databinding.FragmentClassesBinding
import jt.projects.gbschool.model.Lesson
import jt.projects.gbschool.utils.CURRENT_DATE
import jt.projects.gbschool.utils.showSnackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClassesFragment : DaggerFragment() {

    private var _binding: FragmentClassesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: ClassesViewModel

    private val classesAdapter by lazy { ClassesAdapter(::onItemClicked) }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    //    App.instance.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeViewModelData()
        observeLoadingVisible()
    }

    private fun initUi() {
        binding.tvTodayInfo.text = "Today, ${CURRENT_DATE.dayOfMonth} ${CURRENT_DATE.month}"

        with(binding.rvClassesList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = classesAdapter
        }
    }

    private fun observeViewModelData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .resultRecycler
                    .collect {
                        classesAdapter.setData(it)

                        val curLessonIndex = it.indexOf(it.findLast { lesson ->
                            lesson.isCurrent
                        })
                        if (curLessonIndex != -1) {
                            binding.rvClassesList.scrollToPosition(curLessonIndex)
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
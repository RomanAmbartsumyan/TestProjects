package com.example.workmanager.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.WorkInfo
import androidx.work.WorkManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.workmanager.R
import com.example.workmanager.databinding.FragmentMainBinding
import com.example.workmanager.permissions.CheckPermissions


class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()

    private val viewModel: MainFragmentViewModel by viewModels()

    private var isPermissionGranted = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isPermissionGranted = CheckPermissions.checkPermission(requireContext())
        if (isPermissionGranted) {
            observe()
            binding.buttonDownload.setOnClickListener {
                startDownload()
            }

            binding.buttonCancel.setOnClickListener {
                viewModel.stopDownload()
            }
        }
    }

    private fun observe() {
        WorkManager.getInstance(requireContext())
            .getWorkInfosForUniqueWorkLiveData(MainFragmentRepository.WORK_ID)
            .observe(viewLifecycleOwner, {
                if (it.isEmpty()) {
                    handleWorkInfo(it.first())
                }
            })
    }

    private fun startDownload() {
        val url = binding.editTextURL.text.toString()
        viewModel.loadFile(url)
    }

    private fun handleWorkInfo(workInfo: WorkInfo) {
        val isFinished = workInfo.state.isFinished

        with(binding) {
            buttonDownload.isVisible = isFinished
            buttonCancel.isVisible = !isFinished
            progressBar.isVisible = !isFinished
            textView.isVisible = isFinished
        }
    }
}
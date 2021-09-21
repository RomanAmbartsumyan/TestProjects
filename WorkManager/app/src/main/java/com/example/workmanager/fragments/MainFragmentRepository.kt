package com.example.workmanager.fragments


import android.content.Context
import androidx.work.*
import com.example.workmanager.work_manager.DownloadWorker
import java.util.concurrent.TimeUnit

class MainFragmentRepository(private val context: Context) {

    fun loadFile(url: String) {
        val workData = workDataOf(
            DownloadWorker.DOWNLOAD_URL_KEY to url
        )

        val constrainsWorker =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(false)
                .build()

        val workRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setInputData(workData)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 20, TimeUnit.SECONDS)
            .setConstraints(constrainsWorker)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(WORK_ID, ExistingWorkPolicy.KEEP, workRequest)
    }

    fun stopDownload() {
        WorkManager.getInstance(context).cancelUniqueWork(WORK_ID)
    }

    companion object {
        const val WORK_ID = "1243124"
    }

}
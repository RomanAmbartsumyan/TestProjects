package com.example.workmanager.work_manager

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide
import com.example.workmanager.utils.haveQ
import kotlinx.coroutines.delay
import java.net.URL

class DownloadWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val urlToDownload = inputData.getString(DOWNLOAD_URL_KEY)!!
        delay(2000)

        var name = ""
        repeat(7) {
            name += (0..9).random().toString()
        }

        return try {
            val imageUri = saveImageDetails(name)
            downloadImage(urlToDownload, imageUri)
            makeImageVisible(imageUri)

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private fun saveImageDetails(name: String): Uri {
        val volume = if (haveQ()) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val videoDetails = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, name)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (haveQ()) {
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }
        return applicationContext.contentResolver.insert(volume, videoDetails)!!
    }

    private fun downloadImage(url: String, uri: Uri) {
        applicationContext.contentResolver.openOutputStream(uri)?.use { outputStream ->
            URL(url).openStream()
                .use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
        }
    }

    private fun makeImageVisible(imageUri: Uri) {
        if (haveQ()) {
            val imageDetails = ContentValues().apply {
                put(MediaStore.Images.Media.IS_PENDING, 0)
            }
            applicationContext.contentResolver.update(imageUri, imageDetails, null, null)
        }
    }

    companion object {
        const val DOWNLOAD_URL_KEY = "download key"
    }
}
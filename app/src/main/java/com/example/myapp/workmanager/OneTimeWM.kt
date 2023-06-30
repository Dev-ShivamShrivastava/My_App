package com.example.myapp.workmanager

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class OneTimeWM(val context: Context, workerParams: WorkerParameters): Worker(context,workerParams) {

    companion object{
        fun oneTimeWorkManager(context: Context){
            try {
                val oneTimeRequest = OneTimeWorkRequestBuilder<OneTimeWM>().build() // set api calling time, first time immediately call
                WorkManager.getInstance(context).enqueueUniqueWork("OneTimeWM",ExistingWorkPolicy.KEEP,oneTimeRequest)
            }catch (e:Exception){
                e.printStackTrace()
                Result.failure()
            }
        }
    }

    override fun doWork(): Result {
        return try {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            val r = RingtoneManager.getRingtone(context, notification)
            r.play()
            Log.d("ontimeWM","OneTimeWM")
            Result.success()
        }catch (e:Exception){
            Log.d("exception","${e.printStackTrace()}")
            e.printStackTrace()
            Result.failure()
        }
    }


}
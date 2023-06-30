package com.example.myapp.workmanager

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.TimeUnit

class PeriodicWM(val context: Context, workerParams: WorkerParameters): Worker(context,workerParams) {

    companion object{
        fun periodicWorkManager(context: Context) {
            try {

                val periodicWorkRequest = PeriodicWorkRequestBuilder<PeriodicWM>(
                    15,
                    TimeUnit.MINUTES
                ).addTag("Periodic").build()
                val workManager = WorkManager.getInstance(context)
                val future: ListenableFuture<List<WorkInfo>> =
                    workManager.getWorkInfosByTag("Periodic")
                val list: List<WorkInfo>? = future.get()
                if (list.isNullOrEmpty()) {
                    /**
                     * If there is existing pending (uncompleted) work with the same unique name, do nothing.
                     */
                    workManager.enqueueUniquePeriodicWork(
                        "PeriodicWM",
                        ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest
                    )
                    Log.d("workManager", "statusStart")
                } else {
                    Log.d("workManager", "statusLive")
                }
//            val periodicWorkRequest = PeriodicWorkRequestBuilder<PeriodicWM>(
//                24,
//                TimeUnit.HOURS
//            ).build() // set api calling time, first time immediately call
//            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//                "PeriodicWM",
//                ExistingPeriodicWorkPolicy.KEEP,
//                periodicWorkRequest
//            )

            }catch (e:Exception){
                e.printStackTrace()
                Result.failure()
            }
        }
    }

    override fun doWork(): Result {
        return try {
//            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
//            val r = RingtoneManager.getRingtone(context, notification)
//            r.play()
            Log.d("workManager", "success")

            Result.success()
        }catch (e:Exception){
            Log.d("exception","${e.printStackTrace()}")
            e.printStackTrace()
            Result.failure()
        }
    }


}
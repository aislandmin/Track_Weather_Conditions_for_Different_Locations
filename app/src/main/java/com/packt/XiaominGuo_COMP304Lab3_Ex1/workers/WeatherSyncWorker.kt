package com.packt.XiaominGuo_COMP304Lab3_Ex1.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.WeatherRepository

class WeatherSyncWorker (
    appContext: Context,
    workerParams: WorkerParameters,
    private val  weatherRepository: WeatherRepository
    ): CoroutineWorker(appContext, workerParams) {

        override suspend fun doWork(): Result {
            return try {
                weatherRepository.fetchRemoteWeather()
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
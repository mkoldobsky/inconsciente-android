package com.inconsciente.colectiv.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.inconsciente.colectiv.database.getDatabase
import com.inconsciente.colectiv.repository.InconscienteRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.inconsciente.colectiv.work.RefreshDataWorker"
    }
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = InconscienteRepository(database)

        try {
            repository.refreshMessage( )

        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }
}
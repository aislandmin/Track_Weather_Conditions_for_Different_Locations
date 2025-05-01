package com.packt.XiaominGuo_COMP304Lab3_Ex1.di

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.WeatherDatabase
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.WeatherAPI
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.WeatherRepository
import com.packt.XiaominGuo_COMP304Lab3_Ex1.data.WeatherRepositoryImpl
import com.packt.XiaominGuo_COMP304Lab3_Ex1.viewmodel.WeatherViewModel
import com.packt.XiaominGuo_COMP304Lab3_Ex1.workers.WeatherSyncWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

val appModules = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get()) }
    single { Dispatchers.IO }
    single { WeatherViewModel(get()) }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .build()
    }
    single { get<Retrofit>().create(WeatherAPI::class.java) }

    single {
        Room.databaseBuilder(
            androidContext(),
            WeatherDatabase::class.java,
            "weather-database"
        ).build()
    }
    single { get<WeatherDatabase>().weatherDao() }
    worker { WeatherSyncWorker(get(), get(), get()) }

}
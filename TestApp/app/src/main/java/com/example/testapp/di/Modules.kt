package com.example.testapp.di

import com.example.testapp.connection.Api
import com.example.testapp.fragments.main.MainFragmentRepository
import com.example.testapp.fragments.main.MainFragmentRepositoryImpl
import com.example.testapp.fragments.main.MainFragmentViewModel
import com.example.testapp.fragments.register.RegisterFragmentRepository
import com.example.testapp.fragments.register.RegisterFragmentRepositoryImpl
import com.example.testapp.fragments.register.RegisterFragmentViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val viewModelModule = module {
    viewModel { MainFragmentViewModel(get(), get()) }
    viewModel { RegisterFragmentViewModel(get(), get()) }
}

val apiModule = module {
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    single { provideApi(get()) }
}

val connectionModule = module {
    fun provideOkhttpClient() = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .followRedirects(true)
        .build()

    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://dev-exam.l-tech.ru/api/v1/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    single { provideRetrofit(get()) }
    single { provideOkhttpClient() }
}

val repositoryModule = module {
    single<MainFragmentRepository> { MainFragmentRepositoryImpl(get()) }
    single<RegisterFragmentRepository> { RegisterFragmentRepositoryImpl(get()) }
}
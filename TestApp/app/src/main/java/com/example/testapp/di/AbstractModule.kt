package com.example.testapp.di

import com.example.testapp.fragments.main.MainFragmentRepository
import com.example.testapp.fragments.main.MainFragmentRepositoryImpl
import com.example.testapp.fragments.register.RegisterFragmentRepository
import com.example.testapp.fragments.register.RegisterFragmentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AbstractModule {
    @Binds
    abstract fun providesRegisterFragmentRepository(impl: RegisterFragmentRepositoryImpl): RegisterFragmentRepository

    @Binds
    abstract fun providesMainFragmentRepository(impl: MainFragmentRepositoryImpl): MainFragmentRepository
}
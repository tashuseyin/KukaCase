package com.tashuseyin.kukacase.di

import com.tashuseyin.kukacase.data.repository.KukaRepositoryImpl
import com.tashuseyin.kukacase.domain.repository.KukaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindKukaRepository(kukaRepositoryImpl: KukaRepositoryImpl): KukaRepository
}
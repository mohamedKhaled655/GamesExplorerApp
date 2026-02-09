package com.example.gamesexplorerapp.di

import com.example.data.local.LocalDataSource
import com.example.data.local.LocalDataSourceImpl
import com.example.data.remote.RemoteDataSource
import com.example.data.remote.RemoteDataSourceImpl
import com.example.data.repo_impl.GamesRepositoryImpl
import com.example.domain.repo.GamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindGamesRepository(impl: GamesRepositoryImpl): GamesRepository
}
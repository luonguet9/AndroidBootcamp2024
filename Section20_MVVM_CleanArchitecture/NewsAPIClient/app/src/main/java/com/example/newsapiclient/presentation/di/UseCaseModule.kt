package com.example.newsapiclient.presentation.di

import com.example.newsapiclient.domain.repository.NewsRepository
import com.example.newsapiclient.domain.usecase.DeleteSavedNewsUseCase
import com.example.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapiclient.domain.usecase.GetSavedNewsUseCase
import com.example.newsapiclient.domain.usecase.GetSearchedNewsUseCase
import com.example.newsapiclient.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
	@Provides
	@Singleton
	fun provideGetNewsHeadlinesUseCase(
		newsRepository: NewsRepository
	): GetNewsHeadlinesUseCase = GetNewsHeadlinesUseCase(newsRepository)
	
	@Provides
	@Singleton
	fun provideGetSearchedNewsHeadlinesUseCase(
		newsRepository: NewsRepository
	): GetSearchedNewsUseCase = GetSearchedNewsUseCase(newsRepository)
	
	@Provides
	@Singleton
	fun provideSaveNewsUseCase(
		newsRepository: NewsRepository
	): SaveNewsUseCase = SaveNewsUseCase(newsRepository)
	
	@Provides
	@Singleton
	fun provideGetNewsUseCase(
		newsRepository: NewsRepository
	): GetSavedNewsUseCase = GetSavedNewsUseCase(newsRepository)
	
	@Provides
	@Singleton
	fun provideDeleteNewsUseCase(
		newsRepository: NewsRepository
	): DeleteSavedNewsUseCase = DeleteSavedNewsUseCase(newsRepository)
}

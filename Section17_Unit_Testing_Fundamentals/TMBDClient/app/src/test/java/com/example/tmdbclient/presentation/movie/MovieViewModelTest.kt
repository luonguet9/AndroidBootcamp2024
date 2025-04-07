package com.example.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.FakeMovieRepository
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclient.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {
	@get: Rule
	var instantTaskExecutorRule = InstantTaskExecutorRule()
	
	private lateinit var viewModel: MovieViewModel
	
	@Before
	fun setUp() {
		val fakeMovieRepository = FakeMovieRepository()
		val getMoviesUseCase = GetMoviesUseCase(fakeMovieRepository)
		val updateMoviesUseCase = UpdateMoviesUseCase(fakeMovieRepository)
		viewModel = MovieViewModel(getMoviesUseCase, updateMoviesUseCase)
	}
	
	@Test
	fun getMovies_returnsCurrentList() {
		val movies = mutableListOf<Movie>()
		movies.add(Movie(1, "Movie 1", "Overview 1", "poster_path_1", "date_1"))
		movies.add(Movie(2, "Movie 2", "Overview 2", "poster_path_2", "date_2"))
		
		val currentList = viewModel.getMovies().getOrAwaitValue()
		assertThat(currentList).isEqualTo(movies)
	}
	
	@Test
	fun updateMovies_returnsUpdateList() {
		val movies = mutableListOf<Movie>()
		movies.add(Movie(3, "Movie 3", "Overview 3", "poster_path_3", "date_3"))
		movies.add(Movie(4, "Movie 4", "Overview 4", "poster_path_4", "date_4"))
		
		val updateList = viewModel.updateMovies().getOrAwaitValue()
		assertThat(updateList).isEqualTo(movies)
	}
}

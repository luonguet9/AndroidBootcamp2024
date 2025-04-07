package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.domain.repository.MovieRepository

class FakeMovieRepository : MovieRepository {
	private val movies = mutableListOf<Movie>()
	
	init {
		movies.add(Movie(1, "Movie 1", "Overview 1", "poster_path_1", "date_1"))
		movies.add(Movie(2, "Movie 2", "Overview 2", "poster_path_2", "date_2"))
	}
	
	override suspend fun getMovies(): List<Movie>? {
		return movies
	}
	
	override suspend fun updateMovies(): List<Movie>? {
		movies.clear()
		movies.add(Movie(3, "Movie 3", "Overview 3", "poster_path_3", "date_3"))
		movies.add(Movie(4, "Movie 4", "Overview 4", "poster_path_4", "date_4"))
		return movies
	}
	
}

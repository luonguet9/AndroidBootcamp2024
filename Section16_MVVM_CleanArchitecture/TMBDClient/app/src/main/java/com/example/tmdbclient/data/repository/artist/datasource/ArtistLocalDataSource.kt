package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.artist.Artist

interface ArtistLocalDataSource {
	suspend fun getArtistsFromDB() : List<Artist>
	suspend fun saveArtistsToDB(movies: List<Artist>)
	suspend fun clearAll()
}

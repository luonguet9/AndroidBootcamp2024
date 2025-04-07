package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.tvshow.TvShow

@Dao
interface TvShowDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveTvShows(movies: List<TvShow>)
	
	@Query("DELETE FROM popular_tv_shows")
	suspend fun deleteTvShows()
	
	@Query("SELECT * FROM popular_tv_shows")
	suspend fun getTvShows(): List<TvShow>
}

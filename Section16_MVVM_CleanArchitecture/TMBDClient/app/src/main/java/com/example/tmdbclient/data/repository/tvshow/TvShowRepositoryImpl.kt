package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclient.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
	private val tvShowRemoteDataSource: TvShowRemoteDataSource,
	private val tvShowLocalDataSource: TvShowLocalDataSource,
	private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {
	override suspend fun getTvShows(): List<TvShow>? {
		return getTvShowsFromCache()
	}
	
	override suspend fun updateTvShows(): List<TvShow>? {
		val newListOfTvShows = getTvShowsFromAPI()
		tvShowLocalDataSource.clearAll()
		tvShowLocalDataSource.saveTvShowsToDB(newListOfTvShows)
		tvShowCacheDataSource.saveTvShowsToCache(newListOfTvShows)
		return newListOfTvShows
	}
	
	suspend fun getTvShowsFromAPI(): List<TvShow> {
		var tvShowList = listOf<TvShow>()
		try {
			val response = tvShowRemoteDataSource.getTvShows()
			val body = response.body()
			body?.let {
				tvShowList = it.tvShows
			}
		} catch (exception: Exception) {
			Log.i("MyTag", exception.message.toString())
		}
		return tvShowList
	}
	
	suspend fun getTvShowsFromDB(): List<TvShow> {
		var tvShowList = listOf<TvShow>()
		try {
			tvShowList = tvShowLocalDataSource.getTvShowsFromDB()
		} catch (exception: Exception) {
			Log.i("MyTag", exception.message.toString())
		}
		if (tvShowList.isNotEmpty()) {
			return tvShowList
		} else {
			tvShowList = getTvShowsFromAPI()
			tvShowLocalDataSource.saveTvShowsToDB(tvShowList)
		}
		return tvShowList
	}
	
	suspend fun getTvShowsFromCache(): List<TvShow> {
		var tvShowList = listOf<TvShow>()
		try {
			tvShowList = tvShowCacheDataSource.getTvShowsFromCache()
		} catch (exception: Exception) {
			Log.i("MyTag", exception.message.toString())
		}
		if (tvShowList.isNotEmpty()) {
			return tvShowList
		} else {
			tvShowList = getTvShowsFromDB()
			tvShowCacheDataSource.saveTvShowsToCache(tvShowList)
		}
		return tvShowList
	}
}

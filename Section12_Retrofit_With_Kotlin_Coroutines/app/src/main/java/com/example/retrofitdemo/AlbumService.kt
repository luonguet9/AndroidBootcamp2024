package com.example.retrofitdemo

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {
	@GET(value = "/albums")
	suspend fun getAlbums(): Response<Albums>
	
	@GET(value = "/albums")
	suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Albums>
	
	@GET(value = "/albums/{id}")
	suspend fun getAlbum(@Path(value = "id") albumId: Int): Response<AlbumsItem>
	
	@POST(value = "/albums")
	suspend fun uploadAlbum(@Body album: AlbumsItem): Response<AlbumsItem>
}

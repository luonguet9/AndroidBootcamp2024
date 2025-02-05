package com.example.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
	private lateinit var retService: AlbumService
	private lateinit var textView: TextView
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContentView(R.layout.activity_main)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		textView = findViewById(R.id.textView)
		retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

//		getRequestWithQueryParameters()
		updateAlbum()
	}
	
	private fun getRequestWithQueryParameters() {
		val responseLiveData: LiveData<Response<Albums>> = liveData {
			val response = retService.getSortedAlbums(3)
			emit(response)
		}
		responseLiveData.observe(this) {
			val albumsList = it.body()?.listIterator()
			if (albumsList != null) {
				while (albumsList.hasNext()) {
					val albumsItem = albumsList.next()
					Log.i("MY_TAG", "albumsItem: ${albumsItem.title}")
					val result = " " + "Album Title: ${albumsItem.title} " + "\n" +
							" " + "Album id: ${albumsItem.id} " + "\n" +
							" " + "User id: ${albumsItem.userId} " + "\n\n\n"
					textView.append(result)
					
				}
			}
		}
	}
	
	private fun getRequestWithPathParameters() {
		val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
			val response = retService.getAlbum(3)
			emit(response)
		}
		pathResponse.observe(this) {
			val title = it.body()?.title
			Toast.makeText(this, title, Toast.LENGTH_LONG).show()
		}
	}
	
	private fun updateAlbum() {
		val album = AlbumsItem(0, "My title", 3)
		val postResponse: LiveData<Response<AlbumsItem>> = liveData {
			val response = retService.uploadAlbum(album)
			emit(response)
		}
		postResponse.observe(this) {
			val receivedAlbumsItem = it.body()
			val result = " " + "Album Title: ${receivedAlbumsItem?.title} " + "\n" +
					" " + "Album id: ${receivedAlbumsItem?.id} " + "\n" +
					" " + "User id: ${receivedAlbumsItem?.userId} " + "\n\n\n"
			textView.append(result)
		}
		
	}
}

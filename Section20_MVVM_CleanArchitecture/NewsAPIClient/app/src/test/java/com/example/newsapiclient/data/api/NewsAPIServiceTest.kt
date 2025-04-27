package com.example.newsapiclient.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {
	private lateinit var service: NewsAPIService
	private lateinit var server: MockWebServer
	
	@Before
	fun setUp() {
		server = MockWebServer()
		service = Retrofit.Builder()
			.baseUrl(server.url(""))
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(NewsAPIService::class.java)
	}
	
	@After
	fun tearDown() {
		server.shutdown()
	}
	
	private fun enqueueMockResponse(fileName: String) {
		val inputSteam = javaClass.classLoader?.getResourceAsStream(fileName)
		val source = inputSteam?.source()?.buffer()
		val mockResponse = MockResponse()
		source?.let {
			mockResponse.setBody(it.readString(Charsets.UTF_8))
		}
		server.enqueue(mockResponse)
	}
	
	@Test
	fun getTopHeadlines_sendRequest_receivedExpected() {
		runBlocking {
			enqueueMockResponse("newsresponse.json")
			val responseBody = service.getTopHeadlines("us", 1).body()
			val request = server.takeRequest()
			assertThat(responseBody).isNotNull()
			assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=5726456f223b4e429c818b096b402f2b")
		}
	}
	
	@Test
	fun getTopHeadlines_receivedResponse_correctPageSize() {
		runBlocking {
			enqueueMockResponse("newsresponse.json")
			val responseBody = service.getTopHeadlines("us", 1).body()
			val articlesList = responseBody?.articles
			assertThat(articlesList?.size).isEqualTo(20)
		}
	}
	
	@Test
	fun getTopHeadlines_receivedResponse_correctContent() {
		runBlocking {
			enqueueMockResponse("newsresponse.json")
			val responseBody = service.getTopHeadlines("us", 1).body()
			val articlesList = responseBody?.articles
			val article = articlesList?.get(0)
			assertThat(article?.author).isEqualTo("Lee Ying Shan")
			assertThat(article?.url).isEqualTo("https://www.cnbc.com/2025/04/11/asia-pacific-markets-live-trump-trade-war-china-tariffs.html")
			assertThat(article?.publishedAt).isEqualTo("2025-04-11T06:18:00Z")
		}
	}
}

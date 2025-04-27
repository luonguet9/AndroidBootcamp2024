package com.example.newsapiclient

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiclient.data.util.Resource
import com.example.newsapiclient.databinding.FragmentNewsBinding
import com.example.newsapiclient.presentation.adapter.NewsAdapter
import com.example.newsapiclient.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
	private lateinit var viewModel: NewsViewModel
	private lateinit var binding: FragmentNewsBinding
	private lateinit var newsAdapter: NewsAdapter
	private var country = "us"
	private var page = 1
	private var isLoading = false
	private var isScrolling = false
	private var isLastPage = false
	private var pages = 0
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_news, container, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding = FragmentNewsBinding.bind(view)
		viewModel = (activity as MainActivity).viewModel
		newsAdapter = (activity as MainActivity).newsAdapter
		newsAdapter.setOnItemClickListener {
			val bundle = Bundle().apply {
				putSerializable("selected_article", it)
			}
			findNavController().navigate(
				R.id.action_newsFragment_to_infoFragment,
				bundle
			)
		}
		initRecyclerView()
		viewNewsList()
		setSearchView()
	}
	
	private fun initRecyclerView() {
		binding.rvNews.apply {
			adapter = newsAdapter
			layoutManager = LinearLayoutManager(activity)
			addOnScrollListener(this@NewsFragment.onScrollListener)
		}
	}
	
	private fun viewNewsList() {
		viewModel.getNewsHeadLines(country, page)
		viewModel.newsHeadLines.observe(viewLifecycleOwner) { response ->
			when (response) {
				is Resource.Success -> {
					hideProgressBar()
					response.data?.let {
						Log.i("MyTag", "data: ${response.data}")
						newsAdapter.differ.submitList(it.articles.toList())
						pages = if (it.totalResults % 20 == 0) {
							it.totalResults / 20
						} else {
							it.totalResults / 20 + 1
						}
						isLastPage = page == pages
					}
				}
				
				is Resource.Error -> {
					hideProgressBar()
					response.message?.let {
						Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG).show()
					}
				}
				
				is Resource.Loading -> {
					showProgressBar()
				}
			}
		}
	}
	
	private fun showProgressBar() {
		isLoading = true
		binding.progressBar.visibility = View.VISIBLE
	}
	
	private fun hideProgressBar() {
		isLoading = false
		binding.progressBar.visibility = View.INVISIBLE
	}
	
	private val onScrollListener = object : RecyclerView.OnScrollListener() {
		override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
			super.onScrollStateChanged(recyclerView, newState)
			if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
				isScrolling = true
			}
		}
		
		override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
			super.onScrolled(recyclerView, dx, dy)
			val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
			// Tổng số item hiện tại trong danh sách.
			val sizeOfTheCurrentList = layoutManager.itemCount
			// Số lượng item đang hiển thị trên màn hình.
			val visibleItems = layoutManager.childCount
			// Vị trí của item đầu tiên đang hiển thị.
			val topPosition = layoutManager.findFirstVisibleItemPosition()
			// Kiểm tra người dùng đã cuộn đến cuối danh sách hay chưa.
			val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
			val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
			if (shouldPaginate) {
				page++
				viewModel.getNewsHeadLines(country, page)
				isScrolling = false
			}
		}
	}
	
	// Search
	private fun setSearchView() {
		binding.svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(p0: String?): Boolean {
				viewModel.searchNews("us", p0.toString(), page)
				viewSearchViews()
				return false
			}
			
			override fun onQueryTextChange(p0: String?): Boolean {
				MainScope().launch {
					delay(2000)
					viewModel.searchNews("us", p0.toString(), page)
					viewSearchViews()
				}
				return false
			}
		})
		
		binding.svNews.setOnCloseListener {
			initRecyclerView()
			viewSearchViews()
			false
		}
		
	}
	
	private fun viewSearchViews() {
		viewModel.searchedNews.observe(viewLifecycleOwner) { response ->
			when (response) {
				is Resource.Success -> {
					hideProgressBar()
					response.data?.let {
						Log.i("MyTag", "data: ${response.data}")
						newsAdapter.differ.submitList(it.articles.toList())
						pages = if (it.totalResults % 20 == 0) {
							it.totalResults / 20
						} else {
							it.totalResults / 20 + 1
						}
						isLastPage = page == pages
					}
				}
				
				is Resource.Error -> {
					hideProgressBar()
					response.message?.let {
						Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG).show()
					}
				}
				
				is Resource.Loading -> {
					showProgressBar()
				}
			}
		}
	}
	
}

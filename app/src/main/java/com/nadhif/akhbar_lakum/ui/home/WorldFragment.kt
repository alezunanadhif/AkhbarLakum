package com.nadhif.akhbar_lakum.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nadhif.akhbar_lakum.adapter.NewsAdapter
import com.nadhif.akhbar_lakum.data.repository.NewsRepository
import com.nadhif.akhbar_lakum.databinding.FragmentWorldBinding
import com.nadhif.akhbar_lakum.ui.NewsViewModel
import com.nadhif.akhbar_lakum.utils.NewsViewModelFactory

class WorldFragment : Fragment() {
	private lateinit var binding: FragmentWorldBinding
	private val worldNewsViewModel: NewsViewModel by viewModels {
		NewsViewModelFactory(NewsRepository())
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		// Inflate the layout for this fragment
		binding = FragmentWorldBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val mAdapter = NewsAdapter()

		if (worldNewsViewModel.worldNews.value == null){
			worldNewsViewModel.getWorldNews()
		}

		worldNewsViewModel.worldNews.observe(viewLifecycleOwner) {dataNews ->
			mAdapter.setData(dataNews.articles)
			binding.rvWorldNews.apply {
				adapter = mAdapter
				layoutManager = LinearLayoutManager(requireContext())
			}
		}
		worldNewsViewModel.isLoading.observe(viewLifecycleOwner) {
			isLoading(it)
		}
	}

	private fun isLoading(isLoading: Boolean) {
		if (isLoading) {
			binding.loadingView.root.visibility = View.VISIBLE
		} else {
			binding.loadingView.root.visibility = View.GONE
		}
	}
}
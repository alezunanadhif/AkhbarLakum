package com.nadhif.akhbar_lakum.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nadhif.akhbar_lakum.databinding.FragmentWarningForMuslimBinding
import com.nadhif.akhbar_lakum.adapter.NewsAdapter
import com.nadhif.akhbar_lakum.data.repository.NewsRepository
import com.nadhif.akhbar_lakum.ui.NewsViewModel
import com.nadhif.akhbar_lakum.utils.NewsViewModelFactory

class WarningForMuslimFragment : Fragment() {
    lateinit var binding: FragmentWarningForMuslimBinding
    private val commonNewsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWarningForMuslimBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = NewsAdapter()

        if (commonNewsViewModel.warningForMuslimNews.value == null){
            commonNewsViewModel.getWarningNews()
        }

        commonNewsViewModel.warningForMuslimNews.observe(viewLifecycleOwner) {dataNews ->
            mAdapter.setData(dataNews.articles)
            binding.rvWarnForMuslimNews.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        commonNewsViewModel.isLoading.observe(viewLifecycleOwner) {
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
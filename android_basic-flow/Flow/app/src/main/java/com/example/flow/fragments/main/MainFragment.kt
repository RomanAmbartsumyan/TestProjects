package com.example.flow.fragments.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flow.MovieType
import com.example.flow.R
import com.example.flow.databinding.FragmentMainBinding
import com.example.flow.recycler_view.MovieListAdapter
import com.example.flow.utils.autoCleared
import com.example.flow.utils.textChangedFlow
import com.example.flow.utils.toMovieType
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private var movieListAdapter: MovieListAdapter by autoCleared()

    private val mainFragmentViewModel: MainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding = FragmentMainBinding.bind(view)
        initList()
        observes()
        flows()
    }

    private fun flows() {
        viewLifecycleOwner.lifecycleScope.launch {

            val flowEditText = binding.editTextMovieName
                .textChangedFlow()
                .onStart { emit("") }

            val flowMovieType = binding.radioGroupMovie
                .toMovieType()
                .onStart { emit(MovieType.MOVIE) }
            mainFragmentViewModel.bind(flowEditText, flowMovieType)
        }
    }


    private fun initList() {
        movieListAdapter = MovieListAdapter()
        with(binding.movieList) {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observes() {
        mainFragmentViewModel.movieLiveData.observe(viewLifecycleOwner) {
            movieListAdapter.items = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_db -> {
                val action = MainFragmentDirections.actionMainFragmentToMovieFromDBFragment()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
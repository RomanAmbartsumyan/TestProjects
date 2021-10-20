package com.example.flow.fragments.movies_from_db

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flow.R
import com.example.flow.databinding.FragmentMovieFromDbBinding
import com.example.flow.recycler_view.MovieListAdapter
import com.example.flow.utils.autoCleared

class MovieFromDBFragment : Fragment(R.layout.fragment_movie_from_db) {
    private lateinit var binding: FragmentMovieFromDbBinding

    private var movieListAdapter: MovieListAdapter by autoCleared()

    private val movieFromDBViewModel: MovieFromDBViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieFromDbBinding.bind(view)
        movieFromDBViewModel.getMovies()
        initList()
        observes()
    }

    private fun initList() {
        movieListAdapter = MovieListAdapter()
        with(binding.movieFromDB) {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observes() {
        movieFromDBViewModel.moviesFromDBLiveData.observe(viewLifecycleOwner) {
            movieListAdapter.items = it
        }
    }
}
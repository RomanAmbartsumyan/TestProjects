package com.example.testapp.fragments.main

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentMainBinding
import com.example.testapp.objects.Card
import com.example.testapp.utils.autoCleared
import com.example.testapp.utils.cardAdapter
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainFragmentViewModel by viewModel()
    private var cardAdapter: AsyncListDifferDelegationAdapter<Card> by autoCleared()

//    private val cardAdapter = cardAdapter() by autoCleared()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.update -> {
                binding.postList.scrollToPosition(0)
                viewModel.getPosts(isChipChoiceSortedByServer())
                true
            }
            else -> false
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initList()
        if (viewModel.postsLiveData.value?.isNotEmpty() != true) {
            viewModel.getPosts(isChipChoiceSortedByServer())
        }
        chipChoice()
        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                delay(30000)
                launch(Dispatchers.Main) {
                    binding.postList.scrollToPosition(0)
                    viewModel.getPosts(isChipChoiceSortedByServer())
                }
            }
        }
        observes()
    }

    private fun isChipChoiceSortedByServer(): Boolean {
        return binding.chipServerSort.isChecked
    }

    private fun chipChoice() {
        binding.chipDateSort.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.postList.scrollToPosition(0)
                viewModel.sortByDate()
            } else {
                binding.postList.scrollToPosition(0)
                viewModel.sortByDateForApi21()
            }
        }
        binding.chipServerSort.setOnClickListener {
            binding.postList.scrollToPosition(0)
            viewModel.sortByServer()
        }
    }

    private fun observes() {
        viewModel.postsLiveData.observe(viewLifecycleOwner) {
            cardAdapter.items = it
        }
    }

    private fun initList() {
        cardAdapter = cardAdapter { info(it) }
        with(binding.postList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cardAdapter
            setHasFixedSize(true)
        }
    }

    private fun info(card: Card) {
        val action = MainFragmentDirections.actionMainFragmentToInfoFragment(card)
        findNavController().navigate(action)
    }
}

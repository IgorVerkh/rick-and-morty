package com.example.rickandmorty.presentation.characters.ui.characters_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.RickAndMortyApplication
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import com.example.rickandmorty.presentation.episodes.ui.episodes_list.EpisodesListFragment
import com.example.rickandmorty.presentation.locations.ui.locations_list.LocationsListFragment
import com.example.rickandmorty.presentation.mapper.toCharacterListItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class CharactersListFragment : Fragment() {

    private lateinit var binding: FragmentCharactersListBinding

    private val viewModel: CharactersListViewModel by viewModels {
        CharactersListViewModelFactory(activity?.application as RickAndMortyApplication)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharactersListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.bottomNavigation.selectedItemId = R.id.charactersNavItem

        val charactersGridAdapter = CharactersGridAdapter { id ->
            Toast.makeText(context, id.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.charactersGrid.adapter = charactersGridAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.characters.collectLatest {
                    charactersGridAdapter.submitData(it)
                }
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.charactersNavItem -> {
                    activity?.supportFragmentManager?.commit {
                        replace(R.id.fragment_container, CharactersListFragment())
                    }
                    true
                }

                R.id.episodesNavItem -> {
                    activity?.supportFragmentManager?.commit {
                        replace(R.id.fragment_container, EpisodesListFragment())
                    }
                    true
                }

                R.id.locationsNavItem -> {
                    activity?.supportFragmentManager?.commit {
                        replace(R.id.fragment_container, LocationsListFragment())
                    }
                    true
                }

                else -> false

            }
        }

    }

}
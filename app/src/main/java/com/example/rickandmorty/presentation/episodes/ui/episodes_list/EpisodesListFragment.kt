package com.example.rickandmorty.presentation.episodes.ui.episodes_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.ExperimentalPagingApi
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import com.example.rickandmorty.databinding.FragmentEpisodesListBinding
import com.example.rickandmorty.presentation.characters.ui.characters_list.CharactersGridAdapter
import com.example.rickandmorty.presentation.characters.ui.characters_list.CharactersListFragment
import com.example.rickandmorty.presentation.locations.ui.locations_list.LocationsListFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class EpisodesListFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodesListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.bottomNavigation.selectedItemId = R.id.episodesNavItem

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
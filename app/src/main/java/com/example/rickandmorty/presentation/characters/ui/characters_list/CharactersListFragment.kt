package com.example.rickandmorty.presentation.characters.ui.characters_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.RickAndMortyApplication
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import com.example.rickandmorty.presentation.mapper.toCharacterListItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class CharactersListFragment : Fragment() {

    private lateinit var binding: FragmentCharactersListBinding

    @ExperimentalPagingApi
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
    }

}
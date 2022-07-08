package com.example.rickandmorty.presentation.characters.ui.characters_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.RickAndMortyApplication
import com.example.rickandmorty.data.repository.CharactersRepositoryImpl
import com.example.rickandmorty.domain.characters.model.Character
import com.example.rickandmorty.domain.repository.CharactersRepository
import com.example.rickandmorty.presentation.characters.model.CharacterListItem
import com.example.rickandmorty.presentation.mapper.toCharacterListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersListViewModel @ExperimentalPagingApi constructor(
    private val application: RickAndMortyApplication,
    repository: CharactersRepository = CharactersRepositoryImpl(application)
): ViewModel() {

    val characters: Flow<PagingData<CharacterListItem>> = repository.fetchAllCharacters()
        .cachedIn(viewModelScope).map { pagingData ->
            pagingData.map { character ->
                character.toCharacterListItem()
            }
        }

}

@ExperimentalPagingApi
class CharactersListViewModelFactory(
    private val application: RickAndMortyApplication
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharactersListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
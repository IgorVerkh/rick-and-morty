package com.example.rickandmorty

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.rickandmorty.data.repository.CharactersRepositoryImpl
import com.example.rickandmorty.presentation.characters.ui.characters_list.CharactersListViewModel
import com.example.rickandmorty.domain.characters.model.Character
import com.example.rickandmorty.presentation.characters.model.CharacterListItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TestCharactersListModel {

    private val application: RickAndMortyApplication = mockk()
    @ExperimentalPagingApi
    private val repository: CharactersRepositoryImpl = mockk()
    private lateinit var viewModel: CharactersListViewModel

    private lateinit var testCharacter: Character
    private lateinit var testCharacterListItem: CharacterListItem

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    @ExperimentalPagingApi
    fun setup() {
        testCharacter = Character(
            1,
            "Rick",
            "Alive",
            "Human",
            "",
            "Male",
            "https://rickandmortyapi.com/api/location/1",
            "https://rickandmortyapi.com/api/location/20",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            listOf("https://rickandmortyapi.com/api/episode/1")
        )

        testCharacterListItem = CharacterListItem(
            1,
            "Rick",
            "Human",
            "Alive",
            "Male",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )

        every {
            repository.fetchAllCharacters()
        } returns flowOf(PagingData.from(listOf(testCharacter)))

        viewModel = CharactersListViewModel(application, repository)
    }

    @Test
    @ExperimentalPagingApi
    @ExperimentalCoroutinesApi
    fun character_test() = runTest {
        val differ = AsyncPagingDataDiffer<CharacterListItem>(
            diffCallback = object : DiffUtil.ItemCallback<CharacterListItem>() {
                override fun areItemsTheSame(oldItem: CharacterListItem, newItem: CharacterListItem): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: CharacterListItem, newItem: CharacterListItem): Boolean {
                    return oldItem == newItem
                }

            },
            updateCallback = object : ListUpdateCallback {
                override fun onInserted(position: Int, count: Int) {
                }

                override fun onRemoved(position: Int, count: Int) {
                }

                override fun onMoved(fromPosition: Int, toPosition: Int) {
                }

                override fun onChanged(position: Int, count: Int, payload: Any?) {
                }
            },
            workerDispatcher = Dispatchers.Main
        )

        differ.submitData(viewModel.characters.first())

        assertEquals(
            differ.snapshot().items,
            listOf(testCharacterListItem)
        )
    }
}
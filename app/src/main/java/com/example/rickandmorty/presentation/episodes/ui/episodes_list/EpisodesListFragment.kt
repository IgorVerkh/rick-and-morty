package com.example.rickandmorty.presentation.episodes.ui.episodes_list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.R

class EpisodesListFragment : Fragment() {

    companion object {
        fun newInstance() = EpisodesListFragment()
    }

    private lateinit var viewModel: EpisodesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episodes_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EpisodesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
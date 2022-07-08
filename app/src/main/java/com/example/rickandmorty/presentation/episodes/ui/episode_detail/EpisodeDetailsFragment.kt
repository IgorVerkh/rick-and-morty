package com.example.rickandmorty.presentation.episodes.ui.episode_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.R

class EpisodeDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = EpisodeDetailsFragment()
    }

    private lateinit var viewModel: EpisodeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EpisodeDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
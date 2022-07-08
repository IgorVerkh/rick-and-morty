package com.example.rickandmorty.presentation.locations.ui.location_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.R

class LocationDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = LocationDetailsFragment()
    }

    private lateinit var viewModel: LocationDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LocationDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
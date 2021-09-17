package com.example.fidelitytest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_search_anim.*

class SearchAnimFragment : Fragment() {

    private lateinit var viewModel: SearchAnimViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_anim, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = SearchAnimViewModel()

        searchEditText.addTextChangedListener {
            if (it?.length != 0){
            viewModel.getAnims(it.toString())
            } else{
                viewModel.anims.value = emptyList()
            }
        }


        viewModel.anims.observe(viewLifecycleOwner, Observer { searchResults ->
            searchResults?.let {
                searchRv.adapter = AnimAdapter(searchResults)
                (searchRv.adapter as AnimAdapter).notifyDataSetChanged()
            }
        })

        searchEditText.requestFocus()

    }

}
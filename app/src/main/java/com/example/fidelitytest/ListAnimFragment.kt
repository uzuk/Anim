package com.example.fidelitytest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_list_anim.*

class ListAnimFragment : Fragment() {
    private lateinit var viewModel: ListAnimViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_anim, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ListAnimViewModel()

        viewModel.anims.observe(viewLifecycleOwner, Observer { anims ->
            let {
                rV.adapter = AnimAdapter(anims)
            }
        })
        searchAnims.setOnClickListener {
            findNavController().navigate(R.id.searchAnimFragment)
        }
    }

}
package com.valdizz.material2sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.valdizz.material2sample.R

class ChipsFragment : Fragment() {

    companion object {
        fun newInstance() = ChipsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragments_chips, container, false)
    }
}
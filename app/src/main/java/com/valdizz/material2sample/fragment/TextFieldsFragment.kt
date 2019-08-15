package com.valdizz.material2sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.valdizz.material2sample.R

class TextFieldsFragment : Fragment() {

    companion object {
        fun newInstance() = TextFieldsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_text_fields, container, false)
    }
}
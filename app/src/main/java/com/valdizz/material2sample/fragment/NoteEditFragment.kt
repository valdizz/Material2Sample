package com.valdizz.material2sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.valdizz.material2sample.R
import kotlinx.android.synthetic.main.fragment_note_edit.*

class NoteEditFragment : Fragment() {

    companion object {
        private const val ARGS_NOTE = "args_note"
        private const val ARGS_DATE = "args_date"

        fun newInstance(note: String, date: String) = NoteEditFragment().apply {
            arguments = bundleOf(ARGS_NOTE to note, ARGS_DATE to date)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString(ARGS_NOTE).let {
            tvNoteEdit.text = it
        }
        arguments?.getString(ARGS_DATE).let {
            tvNoteDateEdit.text = it
        }
    }
}
package com.valdizz.material2sample.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.valdizz.material2sample.NoteViewModel
import com.valdizz.material2sample.R
import com.valdizz.material2sample.adapter.NoteListAdapter
import com.valdizz.material2sample.common.SortType
import com.valdizz.material2sample.listener.BottomAppBarStateListener
import com.valdizz.material2sample.listener.NoteClickListener
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment() {

    companion object {
        const val TAG = "NotesFragmentTag"
        fun newInstance() = NotesFragment()
    }

    private lateinit var noteViewModel: NoteViewModel
    private var bottomAppBarStateListener: BottomAppBarStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel = activity?.run {
            ViewModelProvider(this).get(NoteViewModel::class.java)
        } ?: throw Exception("Invalid Activity!")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomAppBarStateListener) {
            bottomAppBarStateListener = context
        }
        else {
            throw ClassCastException("$context must implement BottomAppBarStateListener.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = NoteListAdapter(context!!, noteClickListener)
        initRecyclerView(adapter)
        initObserver(adapter)
    }

    private fun initRecyclerView(adapter: NoteListAdapter) {
        rvNotes.adapter = adapter
        rvNotes.layoutManager = LinearLayoutManager(activity)
        rvNotes.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun initObserver(adapter: NoteListAdapter) {
        noteViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let { adapter.setNotes(it) }
        })
        noteViewModel.getNotes(SortType.DEFAULT)
    }

    private val noteClickListener: NoteClickListener = object :
        NoteClickListener {
        override fun onClick(note: String, date: String) {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, NoteEditFragment.newInstance(note, date))
                ?.addToBackStack(null)?.commit()
            bottomAppBarStateListener?.setSecondState()
        }
    }
}


package com.valdizz.material2sample.sheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.valdizz.material2sample.NoteViewModel
import com.valdizz.material2sample.R
import com.valdizz.material2sample.common.SortType
import kotlinx.android.synthetic.main.main_menu_modal_sheet.*

class MainMenuModalSheet : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "MainMenuModalSheet"
        fun newInstance() = MainMenuModalSheet()
    }

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel = activity?.run {
            ViewModelProvider(this).get(NoteViewModel::class.java)
        } ?: throw Exception("Invalid Activity!")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_menu_modal_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigationViewMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_sort_by_default -> {
                    noteViewModel.getNotes(SortType.DEFAULT)
                    navigationViewMenu.setCheckedItem(R.id.nav_sort_by_default)
                }
                R.id.nav_sort_by_name -> {
                    noteViewModel.getNotes(SortType.NAME)
                    navigationViewMenu.setCheckedItem(R.id.nav_sort_by_name)
                }
                R.id.nav_sort_by_date -> {
                    noteViewModel.getNotes(SortType.DATE)
                    navigationViewMenu.setCheckedItem(R.id.nav_sort_by_date)
                }
                R.id.nav_clear_all -> {
                    noteViewModel.deleteAll()
                }
            }

            dismiss()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("ddd", "onOptionsItemSelected")
        return super.onOptionsItemSelected(item)
    }
}
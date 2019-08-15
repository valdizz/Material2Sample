package com.valdizz.material2sample.sheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.valdizz.material2sample.R
import com.valdizz.material2sample.fragment.*
import com.valdizz.material2sample.listener.BottomAppBarStateListener
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.android.synthetic.main.navigation_menu_modal_sheet.*

class NavigationMenuModalSheet : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "NavigationMenuModalSheet"
        fun newInstance() = NavigationMenuModalSheet()
    }

    private var bottomAppBarStateListener: BottomAppBarStateListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomAppBarStateListener) {
            bottomAppBarStateListener = context
        } else {
            throw ClassCastException("$context must implement BottomAppBarStateListener.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.navigation_menu_modal_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_buttons -> {
                    loadFragment(ButtonsFragment.newInstance())
                }
                R.id.nav_cards -> {
                    loadFragment(CardsFragment.newInstance())
                }
                R.id.nav_chips -> {
                    loadFragment(ChipsFragment.newInstance())
                }
                R.id.nav_progress_indicators -> {
                    loadFragment(ProgressFragment.newInstance())
                }
                R.id.nav_selection_controls -> {
                    loadFragment(SelectionFragment.newInstance())
                }
                R.id.nav_text_fields -> {
                    loadFragment(TextFieldsFragment.newInstance())
                }
                R.id.nav_snackbar -> {
                    Snackbar.make(
                        activity?.fab as View,
                        getString(R.string.action_snackbar),
                        Snackbar.LENGTH_LONG
                    ).setAnchorView(R.id.fab).show()
                }
            }
            dismiss()
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.addToBackStack(null)?.commit()
        bottomAppBarStateListener?.setSecondState()
    }
}
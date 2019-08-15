package com.valdizz.material2sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomappbar.BottomAppBar
import com.valdizz.material2sample.fragment.NotesFragment
import com.valdizz.material2sample.listener.BottomAppBarStateListener
import com.valdizz.material2sample.sheet.AddNoteModalSheet
import com.valdizz.material2sample.sheet.MainMenuModalSheet
import com.valdizz.material2sample.sheet.NavigationMenuModalSheet
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity(), BottomAppBarStateListener {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        initBottomAppBar()
        initFab()
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        loadFragment(NotesFragment.newInstance(), NotesFragment.TAG)
    }

    //Set up bottom bar
    private fun initBottomAppBar() {
        setSupportActionBar(bottomAppBar)
        bottomAppBar.replaceMenu(R.menu.bottomappbar_menu)
        bottomAppBar.setNavigationOnClickListener{
            val navigationMenuModalSheet = NavigationMenuModalSheet.newInstance()
            navigationMenuModalSheet.show(supportFragmentManager, NavigationMenuModalSheet.TAG)
        }
    }

    //Set up FAB
    private fun initFab() {
        fab.setOnClickListener {
            if (supportFragmentManager.findFragmentById(R.id.fragment_container)?.tag == NotesFragment.TAG) {
                val addNoteModalSheet = AddNoteModalSheet.newInstance()
                addNoteModalSheet.show(supportFragmentManager, AddNoteModalSheet.TAG)
            } else {
                supportFragmentManager.popBackStack()
                setMainState()
            }
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
            setMainState()
        }
    }

    //Inflate menu to bottom bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.appbar_settings -> {
                val mainMenuModalSheet = MainMenuModalSheet.newInstance()
                mainMenuModalSheet.show(supportFragmentManager, MainMenuModalSheet.TAG)
            }
        }
        return true
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
            .commit()
    }

    override fun setMainState() {
        bottomAppBar.navigationIcon = ContextCompat.getDrawable(this@NoteActivity, R.drawable.ic_menu_white_24dp)
        bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        onCreateOptionsMenu(bottomAppBar.menu)
        fab.setImageResource(R.drawable.ic_add_white_24dp)    }

    override fun setSecondState() {
        bottomAppBar.navigationIcon = null
        bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        bottomAppBar.menu.clear()
        fab.setImageResource(R.drawable.ic_reply_black_24dp)
    }
}

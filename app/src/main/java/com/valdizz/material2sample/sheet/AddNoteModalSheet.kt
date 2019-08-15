package com.valdizz.material2sample.sheet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.valdizz.material2sample.NoteViewModel
import com.valdizz.material2sample.R
import com.valdizz.material2sample.common.Constants.DATE_TIME_FORMAT
import com.valdizz.material2sample.common.Constants.ZERO_TIME
import com.valdizz.material2sample.db.Note
import com.valdizz.material2sample.dialog.DatePickerFragment
import com.valdizz.material2sample.dialog.TimePickerFragment
import kotlinx.android.synthetic.main.add_note_modal_sheet.*
import java.text.SimpleDateFormat
import java.util.*

class AddNoteModalSheet : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "AddNoteModalSheet"
        private const val REQUEST_DATE = 1
        private const val REQUEST_TIME = 2
        fun newInstance() = AddNoteModalSheet()
    }

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.KeyboardDialogStyle)
        noteViewModel = activity?.run {
            ViewModelProvider(this).get(NoteViewModel::class.java)
        } ?: throw Exception("Invalid Activity!")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_note_modal_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        chipDate.setOnClickListener {
            val datePickerDialog = DatePickerFragment.newInstance()
            datePickerDialog.setTargetFragment(this,
                REQUEST_DATE
            )
            datePickerDialog.show(fragmentManager!!, DatePickerFragment.TAG)
        }
        chipTime.setOnClickListener {
            val timePickerDialog = TimePickerFragment.newInstance()
            timePickerDialog.setTargetFragment(this,
                REQUEST_TIME
            )
            timePickerDialog.show(fragmentManager!!, TimePickerFragment.TAG)
        }
        chipDate.setOnCloseIconClickListener {
            chipDate.text = resources.getText(R.string.caption_date)
        }
        chipTime.setOnCloseIconClickListener {
            chipTime.text = resources.getText(R.string.caption_time)
        }
        btnSave.setOnClickListener {
            val sdf = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
            val today = Calendar.getInstance()
            val todayYear = today.get(Calendar.YEAR)
            val todayMonth = today.get(Calendar.MONTH)
            val todayDay = today.get(Calendar.DAY_OF_MONTH)
            val date = if (chipDate.text == getString(R.string.caption_date)) {
                "${if (todayDay < 10) "0$todayDay" else "$todayDay"}.${if (todayMonth < 9) "0${todayMonth + 1}" else "${todayMonth + 1}"}.$todayYear"
            } else {
                chipDate.text.toString()
            }
            val time = if (chipTime.text == getString(R.string.caption_time)) {
                ZERO_TIME
            } else {
                chipTime.text.toString()
            }
            val dateTime = sdf.parse("$date $time")
            val note = Note(0, tvNote.text.toString(), dateTime.time)
            noteViewModel.insert(note)
            dismiss()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_DATE -> {
                    chipDate.text = data?.getStringExtra(DatePickerFragment.TAG_DATE)
                }
                REQUEST_TIME -> {
                    chipTime.text = data?.getStringExtra(TimePickerFragment.TAG_TIME)
                }
            }
        }
    }
}
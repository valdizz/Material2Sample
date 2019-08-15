package com.valdizz.material2sample.dialog

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {

    companion object {
        const val TAG = "DatePickerDialog"
        const val TAG_DATE = "Date"
        fun newInstance() = DatePickerFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val today = Calendar.getInstance()
        return DatePickerDialog(
            context!!,
            datePickerListener,
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )
    }

    private val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        val selectedDate =
            "${if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"}.${if (month < 9) "0${month + 1}" else "${month + 1}"}.$year"
        val intent = Intent().apply {
            putExtra(TAG_DATE, selectedDate)
        }
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
    }
}
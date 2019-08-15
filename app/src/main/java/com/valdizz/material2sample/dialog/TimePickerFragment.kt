package com.valdizz.material2sample.dialog

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment() {

    companion object {
        const val TAG = "TimePickerDialog"
        const val TAG_TIME = "Time"
        fun newInstance() = TimePickerFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val today = Calendar.getInstance()
        return TimePickerDialog(
            context!!,
            timePickerListener,
            today.get(Calendar.HOUR_OF_DAY),
            today.get(Calendar.MINUTE),
            true
        )
    }

    private val timePickerListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
        val selectedTime = "${if (hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"}:${if (minute < 10) "0$minute" else "$minute"}"
        val intent = Intent().apply {
            putExtra(TAG_TIME, selectedTime)
        }
        targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
    }
}
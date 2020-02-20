package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*
import java.util.Calendar.*

private const val ARG_DATE = "date"
class DatePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date: Date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = getInstance()
        calendar.time = date
        val initialYear = calendar.get(YEAR)
        val initialMonth = calendar.get(MONTH)
        val initialDay = calendar.get(DAY_OF_MONTH)
        val initialHour = calendar.get(HOUR_OF_DAY)
        val initialMinute = calendar.get(MINUTE)

        val dateListener = DatePickerDialog.OnDateSetListener {_, year, month, day ->
            TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener{ _, hour, minute ->
                val pickedDateTime = getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                targetFragment?.let { fragment ->
                    (fragment as Callbacks).onDateSelected(pickedDateTime.time)
                }
            }, initialHour, initialMinute, false).show()
        }

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object {

        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }
            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}
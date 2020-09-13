package david.angulo.productsApp.modules.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var onDateSelected: (calendar: Calendar) -> Unit
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var c = arguments?.getSerializable("date") as? Calendar

        if (c == null) {
            c = Calendar.getInstance()
        }

        val year = c!!.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(activity!!, this, year, month, day)
    }

    fun onDateSelected(onDateSet: (calendar: Calendar) -> Unit) {
        this.onDateSelected = onDateSet
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getDefault()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.MONTH, month)
        onDateSelected(calendar)
    }
}
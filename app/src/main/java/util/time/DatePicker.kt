package util.time

import android.app.Activity
import android.app.DatePickerDialog
import util.extensions.day
import util.extensions.month
import util.extensions.year
import java.util.*

object DatePicker {

    operator fun invoke(activity: Activity, day: Int, month: Int, year: Int, l: ((day: Int, month: Int, year: Int) -> Unit)) =
        DatePickerDialog(
            activity,
            { _, y, m, d ->  l(d, m, y) },
            year,
            month,
            day
        )

    operator fun invoke(activity: Activity, calendar: Calendar = Calendar.getInstance(), l: ((day: Int, month: Int, year: Int) -> Unit)) =
        invoke(
            activity,
            calendar.day(),
            calendar.month(),
            calendar.year(),
            l
        )

}
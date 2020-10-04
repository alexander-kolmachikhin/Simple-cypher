package util.time

import android.app.Activity
import android.app.TimePickerDialog
import util.extensions.hour
import util.extensions.minute
import java.util.*

object TimePicker {

    operator fun invoke(activity: Activity, hour: Int, minute: Int, l: ((hour: Int, minute: Int) -> Unit)) =
        TimePickerDialog(
            activity,
            { _, h, m -> l(h, m) },
            hour,
            minute,
            true
        )

    operator fun invoke(activity: Activity, calendar: Calendar = Calendar.getInstance(), l: ((hour: Int, minute: Int) -> Unit)) =
        invoke(activity, calendar.hour(), calendar.minute(), l)

}
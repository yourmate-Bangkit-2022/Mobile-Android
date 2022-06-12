package org.firmanmardiyanto.yourmate.utils

import java.text.SimpleDateFormat
import java.util.*

abstract class CalendarUtils {
    companion object {
        /**
         * Represent date as YEAR-MONTH-DATE
         * Example: 2022-01-31
         * */
        const val YEAR_MONTH_DATE = "yyyy-MM-dd"

        /**
         * Represent date as ISO-8601 representation
         * Example: 2022-03-27T17:00:00.000000Z
         * */
        const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

        /**
         * Represent date as DATE-MONTH-YEAR in human readable representation
         * Example: 28 March 2022
         * */
        const val DATE_MONTH_YEAR_READABLE = "dd MMMM yyyy"

        /**
         * Represent date as DATE-MONTH-YEAR in human readable representation
         * Example: 28 March 2022, 13:00
         * */
        const val DATE_MONTH_YEAR_WITH_TIME_READABLE = "dd MMMM yyyy, HH:mm"

        /**
         * Represent date as HOUR:MINUTE in human readable representation
         * Example: 13:00
         * */
        const val TIME_READABLE = "HH:mm"

        fun formatCalendarToString(calendar: Calendar, pattern: String = "yyyy-MM-dd"): String {
            val format = SimpleDateFormat(pattern, Locale("en", "US"))
            return format.format(calendar.timeInMillis)
        }

        fun formatStringToCalendar(string: String, pattern: String = "yyyy-MM-dd"): Calendar {
            val format = SimpleDateFormat(pattern, Locale("en", "US"))
            return Calendar.getInstance().apply {
                time = format.parse(string) ?: Date()
            }
        }

        fun formatTimeInMilliesToCalendar(timeInMillies: Long): Calendar {
            return Calendar.getInstance().apply {
                timeInMillis = timeInMillies
            }
        }
    }
}
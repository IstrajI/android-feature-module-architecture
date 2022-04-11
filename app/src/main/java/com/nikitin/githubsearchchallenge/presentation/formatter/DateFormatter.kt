package com.nikitin.githubsearchchallenge.presentation.formatter

import android.content.Context
import com.nikitin.githubsearchchallenge.R
import com.nikitin.githubsearchchallenge.presentation.formatter.DateFormatter.Constants.DAYS_IN_MONTH
import com.nikitin.githubsearchchallenge.presentation.formatter.DateFormatter.Constants.INPUT_DATE_FORMAT
import com.nikitin.githubsearchchallenge.presentation.formatter.DateFormatter.Constants.MILLISECONDS_IN_DAY
import com.nikitin.githubsearchchallenge.presentation.formatter.DateFormatter.Constants.ONE_DAY
import com.nikitin.githubsearchchallenge.presentation.formatter.DateFormatter.Constants.RESULT_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateFormatter @Inject constructor(val context: Context) {

    fun getUtcOffsetDateTime(timeString: String): String {
        val time = SimpleDateFormat(INPUT_DATE_FORMAT, Locale.US).parse(timeString)
        val timeDifference = Date().time - time!!.time
        val daysDifference = (timeDifference / MILLISECONDS_IN_DAY).toInt()

        return context.resources.run {
            when (daysDifference) {
                in 0..ONE_DAY -> getString(R.string.updated_title) + " " + getString(R.string.date_formatter_today)
                in ONE_DAY..DAYS_IN_MONTH -> getString(R.string.updated_title) + " " + getQuantityString(
                    R.plurals.date_formatter_days_ago,
                    daysDifference,
                    daysDifference
                )
                else -> getString(R.string.updated_on_title) + " " + SimpleDateFormat(
                    RESULT_DATE_FORMAT, Locale.US
                ).format(time)
            }
        }
    }

    object Constants {
        const val ONE_DAY = 1
        const val DAYS_IN_MONTH = 30
        const val MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24
        const val INPUT_DATE_FORMAT = "yyyy-MM-dd"
        const val RESULT_DATE_FORMAT = "dd MMM yyyy"
    }
}

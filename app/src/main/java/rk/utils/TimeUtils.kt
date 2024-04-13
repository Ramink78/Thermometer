package rk.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime

val tehranTimeZone = TimeZone.of("Asia/Tehran")
fun timestampToReadableFormat(timeStamp: String): String {

    val sampleDateInstant = Instant.parse(timeStamp)
    val now = Clock.System.now()
    val daysUntilTheDate = sampleDateInstant.daysUntil(now, tehranTimeZone)
    println("days : $daysUntilTheDate")
    val diff = now - sampleDateInstant

    return when (daysUntilTheDate) {
        0 -> {
            // in today
            val pastHour = diff.inWholeHours
            if (pastHour == 0L) {
                val pastMinute = diff.inWholeMinutes
                if (pastMinute == 0L) {
                    "A few seconds ago"
                } else
                    "${pastMinute}m Ago"

            } else
                "${pastHour}h Ago"

        }
        1->{
            val time = sampleDateInstant.toLocalDateTime(tehranTimeZone).time.toString().take(5)
            "Yesterday $time"
        }

        in 1..7 -> {
            // in this week
            val dayOfWeek =
                sampleDateInstant.toLocalDateTime(tehranTimeZone).dayOfWeek.toString().lowercase()
                    .replaceFirstChar {
                        it.uppercase()
                    }
            val time = sampleDateInstant.toLocalDateTime(tehranTimeZone).time.toString().take(5)
            "$dayOfWeek $time"
        }

        else -> {
            // longer than a week
            val date = sampleDateInstant.toLocalDateTime(tehranTimeZone).date.toString()
            val time = sampleDateInstant.toLocalDateTime(tehranTimeZone).time.toString().take(5)
            "$date $time"
        }
    }

}


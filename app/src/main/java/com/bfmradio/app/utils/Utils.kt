package com.bfmradio.app.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object Utils {
    const val BASE_URL = "https://bfmcms.s3.ap-southeast-1.amazonaws.com/api/";
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatUnixTime(unixTime: Long): String {
        val instant = Instant.ofEpochSecond(unixTime)
        val formatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a", Locale.getDefault())
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }
    fun getApplicationTitle(): String {
        return "Basic BFM Player"
    }

    fun livestream(): String {
        return "livestream"
    }

    fun podcast(): String {
        return "podcast"
    }

    fun settings(): String {
        return "Settings"
    }

    fun placeHolderGuestDetails(): String {
        return "Guest details go here"
    }

    fun placeHolderTimeStamp(): String {
        return "5 Jan 2024, 10:00am"
    }

    fun lostConnection(): String {
        return "Network is unavailable. Please check your connection."
    }


}
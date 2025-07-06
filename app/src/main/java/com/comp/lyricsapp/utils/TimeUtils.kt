package com.comp.lyricsapp.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatTimestamp(timestamp: Long): String {
    val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a", Locale.getDefault())

    return Instant.ofEpochMilli(timestamp)
        .atZone(ZoneId.systemDefault())
        .format(formatter)
}
package com.example.testapp.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testapp.objects.Card
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(card: Card): Card {
    val localDate = LocalDateTime.parse(card.date.replace("Z".toRegex(), ""))
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss")
    val date = formatter.format(localDate)
    card.date = date
    return card
}

@SuppressLint("SimpleDateFormat")
fun formatDateForApi21(card: Card): Card {
    val localDate = SimpleDateFormat(card.date.replace("Z".toRegex(), ""))
    val formatter = SimpleDateFormat("dd-MM-yyyy, HH:mm:ss")
    val date = formatter.format(localDate)
    card.date = date
    return card
}
package com.darren.practicew3_4.model

import android.graphics.Bitmap

data class Booking(
    val name: String,
    val email: String,
    val photo: Bitmap? = null,
)

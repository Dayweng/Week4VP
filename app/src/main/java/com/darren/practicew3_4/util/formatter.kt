package com.darren.practicew3_4.util

import java.text.NumberFormat
import java.util.Locale

//rupiah formatter
fun formatRupiah(amount: Long): String {
    return NumberFormat.getInstance(Locale("id", "ID")).format(amount)
}
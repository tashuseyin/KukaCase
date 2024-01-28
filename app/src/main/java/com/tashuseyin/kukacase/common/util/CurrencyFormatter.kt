package com.tashuseyin.kukacase.common.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object CurrencyFormatter {
    fun convert(amount: Double?): String {
        val amountValue = amount ?: 0.0
        val locale = Locale("tr", "TR")
        val formatter = DecimalFormat("#,###.##", DecimalFormatSymbols(locale))
        formatter.minimumFractionDigits = 1
        formatter.maximumFractionDigits  = 2
        val formattedValue = formatter.format(amountValue)
        return "$formattedValue €"
    }
}

package com.nikitin.githubsearchchallenge.feature.repositorysearch.entity

import com.nikitin.githubsearchchallenge.feature.repositorysearch.entity.NumberFormatter.Constants.THOUSAND
import com.nikitin.githubsearchchallenge.feature.repositorysearch.entity.NumberFormatter.Constants.THOUSANDS_SYMBOL
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class NumberFormatter @Inject constructor(){
    fun shortFormat(from: Int): String {
        if (from < THOUSAND) return from.toString()
        var remainder = from.toDouble()
        var result = ""
        while (remainder / THOUSAND >= 1) {
            result += THOUSANDS_SYMBOL
            remainder /= THOUSAND
        }
        return ((remainder * 10).roundToInt() / 10.0).toString() + result
    }

    fun commaFormat(from: Int): String {
        return NumberFormat.getInstance(Locale.US).format(from)
    }

    object Constants {
        const val THOUSANDS_SYMBOL = "k"
        const val THOUSAND = 1_000
    }
}
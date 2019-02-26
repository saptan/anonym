package ru.saptan.anonym.domain.model.extensions

import java.util.*

fun Int.formarThousands(): String {
    val suffixes = TreeMap<Int, String>()
    suffixes[1_000] = "K"
    suffixes[1_000_000] = "M"

    if (this == Int.MIN_VALUE) return (Int.MIN_VALUE + 1).formarThousands()
    if (this < 0) return "-" + (-this).formarThousands()
    if (this < 1000) return this.toString()

    val e = suffixes.floorEntry(this)
    val divideBy = e.key
    val suffix = e.value

    val truncated = this / (divideBy / 10)
    val hasDecimal = truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()

    return if (hasDecimal) "${truncated / 10.0}$suffix"
    else "${truncated / 10}$suffix"
}

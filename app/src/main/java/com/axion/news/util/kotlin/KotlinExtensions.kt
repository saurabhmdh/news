package com.axion.news.util.kotlin

fun Int?.orZero(): Int = this ?: 0
fun Int?.orDefault(value: Int): Int = this ?: value
package com.a_ches.timer.data

interface TimestampProvider {
    fun getMilliseconds(): Long
}
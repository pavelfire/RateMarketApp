package com.vk.directop.ratemarketapp.domain.model

import java.time.LocalDateTime

data class IntradayInfo(
    val date: LocalDateTime,
    val open: Double,
    val close: Double,
    val high: Double,
    val low: Double,
    val volume: Int
)

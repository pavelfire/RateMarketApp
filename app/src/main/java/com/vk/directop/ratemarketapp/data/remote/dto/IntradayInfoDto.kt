package com.vk.directop.ratemarketapp.data.remote.dto


data class IntradayInfoDto(
    val timestamp: String,
    val open: Double,
    val close: Double,
    val high: Double,
    val low: Double,
    val volume: Int
)

package com.vk.directop.ratemarketapp.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.vk.directop.ratemarketapp.data.remote.dto.IntradayInfoDto
import com.vk.directop.ratemarketapp.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(
        date = localDateTime,
        open = open,
        close =  close,
        high = high,
        low = low,
        volume = volume,
    )
}
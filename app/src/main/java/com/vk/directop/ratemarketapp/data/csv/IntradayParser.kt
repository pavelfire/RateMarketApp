package com.vk.directop.ratemarketapp.data.csv

import android.os.Build
import androidx.annotation.RequiresApi
import com.opencsv.CSVReader
import com.vk.directop.ratemarketapp.data.mapper.toIntradayInfo
import com.vk.directop.ratemarketapp.data.remote.dto.IntradayInfoDto
import com.vk.directop.ratemarketapp.domain.model.CompanyListing
import com.vk.directop.ratemarketapp.domain.model.IntradayInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntradayParser @Inject constructor(

) : CSVParser<IntradayInfo> {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun parse(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val high = line.getOrNull(1) ?: return@mapNotNull null
                    val low = line.getOrNull(2) ?: return@mapNotNull null
                    val open = line.getOrNull(3) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val volume = line.getOrNull(5) ?: return@mapNotNull null
                    val dto = IntradayInfoDto(
                        timestamp, high.toDouble(), low.toDouble(),
                        open.toDouble(), close.toDouble(), volume.toInt()
                    )
                    dto.toIntradayInfo()
                }
                .filter{
                    it.date.dayOfMonth == LocalDateTime.now().minusDays(1).dayOfMonth
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }


}
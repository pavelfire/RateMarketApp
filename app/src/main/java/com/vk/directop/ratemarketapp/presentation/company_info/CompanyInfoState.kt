package com.vk.directop.ratemarketapp.presentation.company_info

import com.vk.directop.ratemarketapp.domain.model.CompanyInfo
import com.vk.directop.ratemarketapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

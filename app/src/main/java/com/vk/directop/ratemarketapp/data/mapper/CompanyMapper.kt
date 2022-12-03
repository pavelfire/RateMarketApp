package com.vk.directop.ratemarketapp.data.mapper


import com.vk.directop.ratemarketapp.data.local.CompanyListingEntity
import com.vk.directop.ratemarketapp.data.remote.dto.CompanyInfoDto
import com.vk.directop.ratemarketapp.domain.model.CompanyInfo
import com.vk.directop.ratemarketapp.domain.model.CompanyListing


fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}
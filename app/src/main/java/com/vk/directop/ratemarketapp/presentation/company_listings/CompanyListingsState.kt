package com.vk.directop.ratemarketapp.presentation.company_listings

import com.vk.directop.ratemarketapp.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)

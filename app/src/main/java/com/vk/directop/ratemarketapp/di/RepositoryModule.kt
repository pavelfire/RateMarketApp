package com.vk.directop.ratemarketapp.di

import com.vk.directop.ratemarketapp.data.csv.CSVParser
import com.vk.directop.ratemarketapp.data.csv.CompanyListingsParser
import com.vk.directop.ratemarketapp.data.repository.StockRepositoryImpl
import com.vk.directop.ratemarketapp.domain.model.CompanyListing
import com.vk.directop.ratemarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}
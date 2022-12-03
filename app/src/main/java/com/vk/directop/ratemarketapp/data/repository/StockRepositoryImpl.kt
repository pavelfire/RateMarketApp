package com.vk.directop.ratemarketapp.data.repository

import com.opencsv.CSVReader
import com.vk.directop.ratemarketapp.data.csv.CSVParser
import com.vk.directop.ratemarketapp.data.csv.CompanyListingsParser
import com.vk.directop.ratemarketapp.data.local.StockDatabase
import com.vk.directop.ratemarketapp.data.mapper.toCompanyInfo
import com.vk.directop.ratemarketapp.data.mapper.toCompanyListing
import com.vk.directop.ratemarketapp.data.mapper.toCompanyListingEntity
import com.vk.directop.ratemarketapp.data.remote.StockApi
import com.vk.directop.ratemarketapp.domain.model.CompanyInfo
import com.vk.directop.ratemarketapp.domain.model.CompanyListing
import com.vk.directop.ratemarketapp.domain.model.IntradayInfo
import com.vk.directop.ratemarketapp.domain.repository.StockRepository
import com.vk.directop.ratemarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>,
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                //val csvReader = CSVReader(InputStreamReader(response.byteStream()))
                companyListingsParser.parse(response.byteStream())
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing()}
                ))
                emit(Resource.Loading(false))

            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            var response = api.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            Resource.Success(results)
        }catch (e: IOException){
            e.printStackTrace()
            Resource.Error("Couldn't load intraday info")
        }catch (e: HttpException){
            e.printStackTrace()
            Resource.Error("Couldn't load data")
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Resource.Success(result.toCompanyInfo())

        }catch (e: IOException){
            e.printStackTrace()
            Resource.Error("Couldn't load company info")
        }catch (e: HttpException){
            e.printStackTrace()
            Resource.Error("Couldn't load company info")
        }
    }

}
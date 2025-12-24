package com.norah.nooralhadith.domain.repository

interface HadithRepository {
    suspend fun getHadithMatn(collection: String, number: Int): String
}
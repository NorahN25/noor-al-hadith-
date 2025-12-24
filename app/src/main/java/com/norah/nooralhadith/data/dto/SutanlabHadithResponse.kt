package com.norah.nooralhadith.data.dto

data class SutanlabHadithResponse(
    val data: Data? = null
) {
    data class Data(
        val name: String? = null,
        val id: String? = null,
        val available: Int? = null,
        val requested: Int? = null,
        val hadith: Hadith? = null
    )

    data class Hadith(
        val number: Int? = null,
        val arab: String? = null,
        val id: String? = null
    )
}
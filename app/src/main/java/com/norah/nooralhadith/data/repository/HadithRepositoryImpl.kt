package com.norah.nooralhadith.data.repository

import com.norah.nooralhadith.data.remote.HadithApi
import com.norah.nooralhadith.domain.repository.HadithRepository

class HadithRepositoryImpl(
    private val api: HadithApi
) : HadithRepository {

    override suspend fun getHadithMatn(collection: String, number: Int): String {
        val url = "gh/fawazahmed0/hadith-api@1/editions/$collection.json"
        val json = api.getEditionJson(url)

        val hadiths = json["hadiths"] as? List<*> ?: error("No hadiths in response")

        val found = hadiths.firstOrNull { item ->
            val obj = item as? Map<*, *> ?: return@firstOrNull false
            val n = (obj["hadithnumber"] as? Number)?.toInt()
                ?: (obj["hadithNumber"] as? Number)?.toInt()
                ?: (obj["number"] as? Number)?.toInt()
            n == number
        } as? Map<*, *> ?: error("Hadith not found: $collection #$number")

        val text = found["text"]?.toString()
            ?: found["hadith"]?.toString()
            ?: found["body"]?.toString()
            ?: ""

        if (text.isBlank()) error("Empty hadith text")
        return text
    }
}
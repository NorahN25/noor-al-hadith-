package com.norah.nooralhadith.domain.model

data class HadithDetailsModel(

    val id: Int,

    val title: String,

    val narrator: String,

    val matn: String,        // ✅ من API

    val kidsExplain: String, // ✅ محلي

    val imageResId: Int      // ✅ محلي

)

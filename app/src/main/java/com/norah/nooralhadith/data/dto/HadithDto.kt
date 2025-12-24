package com.norah.nooralhadith.data.dto

data class HadithDto(

    val id: Int,

    val title: String,

    val narrator: String

)

data class HadithDetailsDto(

    val id: Int,

    val title: String,

    val narrator: String,

    val matn: String

)

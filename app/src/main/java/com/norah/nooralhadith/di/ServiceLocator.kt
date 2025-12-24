package com.norah.nooralhadith.di

import com.norah.nooralhadith.data.remote.RetrofitProvider
import com.norah.nooralhadith.data.repository.HadithRepositoryImpl
import com.norah.nooralhadith.domain.repository.HadithRepository

object ServiceLocator {
    val hadithRepository: HadithRepository by lazy {
        HadithRepositoryImpl(RetrofitProvider.api)
    }
}
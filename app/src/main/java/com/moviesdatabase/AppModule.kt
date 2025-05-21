package com.moviesdatabase

import com.common.network.ApiEndpoints
import com.common.network.AuthType
import com.common.network.BaseApiClientV2
import org.koin.dsl.module

val appModule = module {

    single {
        BaseApiClientV2().getClient(ApiEndpoints.API_URL_GITHUB, AuthType.Custom(hashMapOf()))
    }

//    single {
//        get<Retrofit>().create(AuthRetrofitService::class.java)
//    }
//    single {
//        AuthRetrofitServiceImpl(get())
//    }
//    single {
//        AuthUseCase(get())
//    }
//    viewModel { EnterEmailViewModel(get()) }
//    viewModel { LoginViewModel(get(),get(named(DataStores.USER))) }
//    viewModel { RegisterViewModel(get(),get(named(DataStores.USER))) }
//
//
//    single {
//        get<Retrofit>().create(HomeRetrofitService::class.java)
//    }
//    single {
//        HomeRetrofitServiceImpl(get())
//    }
//    single {
//        HomeUseCase(get())
//    }
//    viewModel { HomeViewModel(get()) }
//
//    viewModel { ProfileViewModel() }
//    viewModel { ListingViewModel() }

}
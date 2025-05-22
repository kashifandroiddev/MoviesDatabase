package com.moviesdatabase

import android.util.Log
import com.data.mapper.MovieMapper
import com.data.movies.datasource.LocalMoviesDataSource
import com.data.movies.datasource.MoviesDataSource
import com.data.movies.datasource.RemoteMoviesDataSource
import com.data.movies.repositories.MoviesRepositoryImp
import com.domain.repositories.MovieRepository
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import org.koin.androidx.viewmodel.dsl.viewModel
import com.domain.usecase.SearchMoviesUseCase
import com.presentation.SearchMoviesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.defaultRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val AppModule = module {
    single { Dispatchers.IO }
    single { CoroutineScope(get()) }
}

val NetworkModule = module {
    single {
        HttpClient(Android) {

            defaultRequest {
                url {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.API_URL
                }
            }

            install(Logging) { level = LogLevel.ALL }

            install(ContentNegotiation) {
                gson {
                    serializeNulls()
                }
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            BuildConfig.TMDB_TOKEN,
                            BuildConfig.TMDB_TOKEN
                        )
                    }
                }
            }

            HttpResponseValidator {
                validateResponse {
                    when (it.status.value) {
                        in 300..399 -> {}
                        in 400..499 -> {
                            Log.d("TAG", "${it.status.value}")
                        }

                        in 500..599 -> {}
                    }
                }
                handleResponseExceptionWithRequest { cause: Throwable, request: HttpRequest ->
                    Log.d("TAG", "$cause: $request")
                }
            }

            install(ResponseObserver) {
                onResponse { response ->
                    println("HTTP status: ${response.status.value}")
                }
            }

        }
    }
}

val mediaModule = module {
    single { MovieMapper() }

    single<MoviesDataSource>(named("LocalMoviesDataSource")) { LocalMoviesDataSource() }

    single<MoviesDataSource>(named("RemoteMoviesDataSource")) { RemoteMoviesDataSource(get()) }

    single<MovieRepository> {
        MoviesRepositoryImp(
            movieMapper = get(),
            ioDispatcher = get(),
            remoteMoviesDataSource = get(named("RemoteMoviesDataSource")),
            localMoviesDataSource = get(named("LocalMoviesDataSource")),
        )
    }

    single { SearchMoviesUseCase(get()) }

    viewModel { SearchMoviesViewModel(get()) }

}

//val mediaDetailModule = module {
//
////    viewModel { MediaDetailViewModel() }
//}

//val appModule = module {
//
//    single {
//        BaseApiClientV2().getClient(
//            BuildConfig.API_URL, AuthType.Custom(
//                hashMapOf(
//                    "Authorization" to "Bearer ${BuildConfig.TMDB_TOKEN}"
//                )
//            )
//        )
//    }
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


//}
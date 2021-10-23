package castelles.com.github.api.datasource.model

import castelles.com.github.api.BuildConfig
import castelles.com.github.api.utils.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class DataSource(private val baseUrl: String = BuildConfig.BASE_URL) {

    protected lateinit var retrofit: Retrofit
    private var token: String? = null

    init {
        build()
    }

    fun setToken(value: String) {
        token = value
    }

    private fun build() {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val log = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            client.addInterceptor(log)
        }

        client.addInterceptor(AuthInterceptor(token))

        retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    protected fun <T> getResponse(
        response: Response<T>
    ): NetworkFetcher<T> = try {
            if (response.isSuccessful)
                Success(response.body())
            else
                Error(ErrorHandler(response))
        } catch (e: Exception) {
            Error<Exception>(ErrorHandler(exception = e))
        }

}
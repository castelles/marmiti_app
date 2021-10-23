package castelles.com.github.api.utils

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class AuthInterceptor(private val token: String?): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val newBuilder = token?.let { tk ->
            original
                .newBuilder()
                .addHeader("Authorization", "Bearer $tk")
        }

        return try {
            chain.proceed(newBuilder?.build() ?: original.newBuilder().build())
        } catch (e: Exception) {
            sendError(chain, e)
        }
    }

    private fun sendError(chain: Interceptor.Chain, e: Exception): Response {
        val type = "application/json; charset=utf-8"
        val error = "{ \"Error\": \"${e.message ?: e.stackTraceToString()}\" }"
        return Response
            .Builder()
            .code(500)
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .body(ResponseBody.create(type.toMediaType(), error))
            .message(error).build()
    }

}
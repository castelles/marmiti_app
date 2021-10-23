package castelles.com.github.api.utils

import retrofit2.Response
import java.nio.charset.Charset

class ErrorHandler<T>(
    private val response: Response<T>? = null,
    private val exception: Exception? = null
) {

    var errorBody: String? = null
    private set

    var code = 0
    private set

    var message: String? = null
    private set

    init { destruct() }

    private fun destruct() {
        response?.apply {
            code = code()
            errorBody = errorBody()?.byteString()?.string(Charset.defaultCharset())
            message = message()
        } ?: run {
            exception?.let {
                code = 500
                errorBody = it.cause.toString()
                message = it.message.orEmpty()
            }
        }
    }

    fun toThrowable(): Throwable =
        Throwable(
            message = errorBody,
            Throwable(code.toString())
        )

    override fun toString(): String {
        return "{ code: $code, error: ${errorBody.orEmpty()}, message: ${message.orEmpty()}"
    }
}
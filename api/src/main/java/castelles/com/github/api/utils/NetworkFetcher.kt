package castelles.com.github.api.utils

sealed class NetworkFetcher<out T>()

data class Success<T>(val result: T?) : NetworkFetcher<T>()

data class Error<T>(val error: ErrorHandler<T>): NetworkFetcher<Nothing>()

object Loading: NetworkFetcher<Nothing>()

package castelles.com.github.api.repository.contract

import castelles.com.github.api.model.UserResponse
import castelles.com.github.api.utils.NetworkFetcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getNotAuthenticatedUser(
        username: String,
        dispatcher: CoroutineDispatcher = IO
    ): Flow<NetworkFetcher<UserResponse>>

}
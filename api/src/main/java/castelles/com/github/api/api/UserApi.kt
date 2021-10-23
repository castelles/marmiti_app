package castelles.com.github.api.api

import castelles.com.github.api.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{username}")
    suspend fun getNotAuthenticatedUser(
        @Path("username") username: String
    ): Response<UserResponse>

}
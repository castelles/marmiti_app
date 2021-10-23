package castelles.com.github.api.datasource

import castelles.com.github.api.api.UserApi
import castelles.com.github.api.datasource.model.DataSource

class UserDataSource: DataSource() {

    private val api = retrofit.create(UserApi::class.java)

    suspend fun getNotAuthenticatedUser(username: String) =
        getResponse(api.getNotAuthenticatedUser(username))
}
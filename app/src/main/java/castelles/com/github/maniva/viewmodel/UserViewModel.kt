package castelles.com.github.maniva.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castelles.com.github.maniva.viewmodel.state.State
import castelles.com.github.api.model.UserResponse
import castelles.com.github.api.repository.contract.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
): ViewModel() {

    private val _userFetcher = MutableStateFlow(State<UserResponse>())
    val userFetcher = _userFetcher.asStateFlow()

    fun getUserInformation() {
        /**
         * Here you place your github username
         */
        val username = "castelles"
        viewModelScope.launch {
            repository.getNotAuthenticatedUser(username).collect {
                _userFetcher.value = _userFetcher.value.copy(it)
            }
        }
    }

}
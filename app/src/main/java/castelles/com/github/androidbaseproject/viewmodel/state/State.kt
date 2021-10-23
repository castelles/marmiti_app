package castelles.com.github.androidbaseproject.viewmodel.state

import castelles.com.github.api.utils.NetworkFetcher

data class State<T>(val handler: NetworkFetcher<T>? = null)

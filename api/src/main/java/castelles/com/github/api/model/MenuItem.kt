package castelles.com.github.api.model

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val available: Boolean = true,
    val quantityAvailable: Int
)

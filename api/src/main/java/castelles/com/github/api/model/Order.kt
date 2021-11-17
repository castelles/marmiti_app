package castelles.com.github.api.model

data class Order(
    val id: Int,
    val open: Boolean,
    val total: Double,
    val items: List<OrderItem>
)

data class OrderItem(
    val quantity: Int,
    val description: String
)
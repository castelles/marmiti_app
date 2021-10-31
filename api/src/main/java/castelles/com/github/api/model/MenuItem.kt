package castelles.com.github.api.model

import java.io.Serializable

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val available: Boolean = true,
    val takeAwayAvailable: Boolean = true,
    val quantityAvailable: Int,
    val favorite: Boolean = false,
    val image: Int,
    val promotion: Promotion = Promotion()
): Serializable

data class Promotion(
    val promotion: Boolean = false,
    val pricePromotion: Double = 0.00
)

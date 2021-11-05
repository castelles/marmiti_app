package castelles.com.github.maniva.util

fun Double.toMoneyDecimal(): String {
    return "R$ " + String.format("%.2f", this)
}
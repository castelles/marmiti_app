package castelles.com.github.androidbaseproject.util

fun Double.toMoneyDecimal(): String {
    return "R$ " + String.format("%.2f", this)
}
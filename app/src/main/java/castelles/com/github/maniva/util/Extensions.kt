package castelles.com.github.maniva.util

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import androidx.navigation.*
import androidx.viewpager.widget.ViewPager
import castelles.com.github.api.model.MenuItem
import castelles.com.github.api.model.Order
import castelles.com.github.api.model.OrderItem
import castelles.com.github.maniva.R
import java.lang.IllegalArgumentException

fun Double.toMoneyDecimal(): String {
    return "R$ " + String.format("%.2f", this)
}

fun android.view.MenuItem.navigate(navController: NavController): Boolean {

    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
    if (navController.currentDestination!!.parent!!.findNode(itemId) is ActivityNavigator.Destination) {
        builder.setEnterAnim(R.anim.slide_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out)
    } else {
        builder.setEnterAnim(R.anim.slide_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out)
    }
    if (order and Menu.CATEGORY_SECONDARY == 0) {
        builder.setPopUpTo(findStartDestination(navController.graph).id, false)
    }
    val options = builder.build()
    return try {
        navController.navigate(itemId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }

}

private fun findStartDestination(navGraph: NavGraph): NavDestination {
    var startDestination: NavDestination = navGraph
    while (startDestination is NavGraph) {
        val parent = startDestination
        startDestination = parent.findNode(parent.startDestination)!!
    }
    return startDestination
}

fun ViewPager.autoScroll(interval: Long) {
    var scrollPosition = 0
    val handler = Handler()

    val runnable = object : Runnable {

        override fun run() {
            val count = adapter?.count ?: 0
            setCurrentItem(scrollPosition++ % count, true)
            handler.postDelayed(this, interval)
        }

    }

    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageSelected(position: Int) {
            scrollPosition = position + 1
        }
    })

    handler.post(runnable)
}

fun Context.hideKeyboard() {
    try {
        (this as Activity).currentFocus?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.generateList(): MutableList<MenuItem> {
    val items = mutableListOf<MenuItem>()
    items.add(
        MenuItem(
            1,
            "Lasanha vegetariana clássica",
            "Massa comum, bolonhesa de soja, molho bechamel clássico e queijo.",
            22.00,
            true,
            true,
            5,
            image = R.drawable.carousel_lasanha
        )
    )

    items.add(
        MenuItem(
            1,
            "Marmita Brasileira",
            "Picadinho de soja com cenoura e pimentão, purê de batatas e arroz branco ou integral.",
            12.00,
            true,
            true,
            5,
            image = R.drawable.marmiti_brasileira
        )
    )

    items.add(
        MenuItem(
            1,
            "Marmita Francesa",
            "Massa de panqueca recheada com bolonhesa de soja. Legumes saltados e arroz branco.",
            12.00,
            true,
            true,
            5,
            image = R.drawable.marmiti_brasileira
        )
    )

    items.add(
        MenuItem(
            1,
            "Marmita Árabe",
            "3 quibes de soja, hummus de grão de bico e arroz branco ou integral.",
            15.00,
            true,
            true,
            5,
            image = R.drawable.marmiti_brasileira
        )
    )

    items.add(
        MenuItem(
            1,
            "Marmita Italiana",
            "Massa talharim, molho de tomate e três almôndegas de soja recheadas com queijo.",
            12.00,
            true,
            true,
            5,
            image = R.drawable.marmiti_brasileira
        )
    )
    return items
}

fun Context.generateOrders(): MutableList<Order> {
    val orders = mutableListOf<Order>()

    val itemsOne = OrderItem(
        1,
        "Marmita brasileira"
    )

    val itemsTwo = OrderItem(
        1,
        "Marmita Árabe"
    )

    val itemsThree = OrderItem(
        1,
        "Lasanha vegetariana clássica"
    )

    val itemsFour = OrderItem(
        2,
        "Marmita italiana"
    )

    orders.add(Order(
        2331,
        true,
        27.0,
        mutableListOf(itemsOne, itemsTwo)
    ))

    orders.add(
        Order(
            2330,
            false,
            22.0,
            listOf(itemsThree)
        )
    )

    orders.add(
        Order(
            2329,
            false,
            24.0,
            listOf(itemsFour, itemsThree)
        )
    )
    return orders
}
package castelles.com.github.maniva.ui.adapter

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import castelles.com.github.api.model.OrderItem
import castelles.com.github.maniva.R
import castelles.com.github.maniva.util.toMoneyDecimal


@BindingAdapter("imageRes")
fun setImage(view: ImageView, image: Int?) {
    image?.let { img ->
        if (img != 0) {
            val imageDrawable = view.context.getDrawable(img)
            view.setImageDrawable(imageDrawable)
        }
    }
}

@BindingAdapter("price")
fun setPrice(view: TextView, value: Double?) {
    value?.let { _value ->
        view.text = _value.toMoneyDecimal()
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@BindingAdapter("status")
fun setOrderStatus(view: TextView, open: Boolean) {
    view.apply {
        text = if (open) {
            setBackgroundColor(context.getColor(R.color.status_green))
            "Aberto"
        } else {
            "Fechado"
        }
    }
}

@BindingAdapter("orderItems")
fun setOrderItems(view: TextView, items: List<OrderItem>) {
    var content = ""
    items.forEach { item ->
        content += "${item.quantity}x ${item.description}\n"
    }

    view.text = content
}
package castelles.com.github.androidbaseproject.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import castelles.com.github.androidbaseproject.util.toMoneyDecimal


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
package castelles.com.github.maniva.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import castelles.com.github.maniva.R

class CarouselAdapter(private val context: Context): PagerAdapter() {

    override fun getCount(): Int = IMAGES.size

    override fun startUpdate(container: ViewGroup) {
        super.startUpdate(container)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image = ImageView(context)
        image.apply {
            setImageDrawable(context.getDrawable(IMAGES[position]))
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_XY
        }
        (container as ViewPager).addView(image)
        return image
    }

    companion object {
        val IMAGES = mutableListOf(
            R.drawable.carousel_lasanha,
            R.drawable.carousel_quibe
        )
    }
}
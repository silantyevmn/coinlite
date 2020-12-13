package silantyevmn.ru.coinlite.mvp.model.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_chart.*

class ImageLoaderGlade: ImageLoader<ImageView>{
    override fun loadInto(url: String, view: ImageView) {
        Glide.with(view.context).load(url).into(view)
    }
}
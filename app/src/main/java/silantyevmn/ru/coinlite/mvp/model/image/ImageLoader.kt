package silantyevmn.ru.coinlite.mvp.model.image

import androidx.annotation.Nullable

interface ImageLoader<T> {
    fun loadInto(@Nullable url:String,view: T);
}
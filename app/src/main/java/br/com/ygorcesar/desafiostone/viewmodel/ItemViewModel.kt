package br.com.ygorcesar.desafiostone.viewmodel

import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.widget.ImageView
import br.com.ygorcesar.desafiostone.data.loadImage
import br.com.ygorcesar.desafiostone.data.toCurrencyBRL
import br.com.ygorcesar.desafiostone.model.Item

@BindingAdapter("bindImage")
fun bindThumbnail(iv: ImageView, url: String) {
    iv.loadImage(url)
}

class ItemViewModel(private var item: Item) : BaseObservable() {

    fun setItem(item: Item) {
        this.item = item
        notifyChange()
    }

    fun getItem() = this.item

    fun getTitle() = item.title

    fun getSeller() = item.seller

    fun getPrice() = item.price.toCurrencyBRL()

    fun getThumbnailUrl() = item.thumbnailHd
}
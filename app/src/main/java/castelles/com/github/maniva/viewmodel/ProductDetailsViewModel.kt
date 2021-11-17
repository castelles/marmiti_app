package castelles.com.github.maniva.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import castelles.com.github.api.model.MenuItem
import castelles.com.github.maniva.util.toMoneyDecimal
import castelles.com.github.maniva.viewmodel.event.LiveClick

class ProductDetailsViewModel: ViewModel() {

    private val _product = MutableLiveData<MenuItem>()
    val productFetcher: LiveData<MenuItem> = _product

    private val _quantity = MutableLiveData(1)
    val quantity: LiveData<Int> = _quantity

    private val _priceTotal = MutableLiveData<String>()
    val priceTotal: LiveData<String> = _priceTotal

    val removeItemClick = LiveClick<Unit>()
    val addItemClick = LiveClick<Unit>()
    val addToCartClick = LiveClick<Unit>()
    val backClick = LiveClick<Unit>()

    fun removeItem() {
        quantity.value?.let { amount ->
            if (amount > 1) {
                _quantity.postValue(amount - 1)
                _priceTotal.postValue(
                    (productFetcher.value!!.price * (amount - 1)).toMoneyDecimal()
                )
            }
            else {}
        }
    }
    fun addItem() {
        quantity.value?.let { amount ->
            if (amount < MAX_QUANTITY) {
                _quantity.postValue(amount + 1)
                _priceTotal.postValue(
                    (productFetcher.value!!.price * (amount + 1)).toMoneyDecimal()
                )
            }
            else {}
        }
    }

    fun setProduct(product: MenuItem) {
        _product.postValue(product)
        _priceTotal.postValue(product.price.toMoneyDecimal())
    }

    fun updateQuantity(value: Int) { _quantity.postValue(value) }

    fun addToCart() {
        addToCartClick.sendAction(Unit)
    }

    companion object {
        const val MAX_QUANTITY = 3
    }
}
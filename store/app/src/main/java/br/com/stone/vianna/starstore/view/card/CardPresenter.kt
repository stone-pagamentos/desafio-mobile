package br.com.stone.vianna.starstore.view.card

import br.com.stone.vianna.starstore.entity.PaymentRequest
import br.com.stone.vianna.starstore.entity.PaymentTransaction
import br.com.stone.vianna.starstore.extensions.*
import br.com.stone.vianna.starstore.view.itemList.ItemListRepository

class CardPresenter(private val view: CardContract.View,
                    private val paymentRepository: PaymentRepository,
                    private val itemListRepository: ItemListRepository
) : CardContract.Presenter {

    var value = 0

    override fun init(value: Int) {
        this.value = value
    }

    override fun onCheckoutButtonClicked(paymentRequest: PaymentRequest) {

        var isFormValid = true

        when {
            paymentRequest.cardNumber.isEmpty()
                    || !paymentRequest.cardNumber.isValidCardLength() -> {
                isFormValid = isFormValid && false
                view.displayCardNumberError()
            }
            else -> {
                isFormValid = isFormValid && true
                view.hideCardNumberError()
            }
        }

        when {
            paymentRequest.expirationDate.isEmpty()
                    || !validateCardExpiryDate(paymentRequest.expirationDate)
                    || isDateExpired(paymentRequest.expirationDate) -> {
                isFormValid = isFormValid && false
                view.displayCardExpirationDateError()
            }
            else -> {
                isFormValid = isFormValid && true
                view.hideCardExpirationDateError()
            }
        }

        when {
            paymentRequest.cardHolder.isEmpty()
                    || !paymentRequest.cardHolder.isValidNameLength() -> {
                isFormValid = isFormValid && false
                view.displayCardHolderError()
            }
            else -> {
                isFormValid = isFormValid && true
                view.hideCardHolderError()
            }
        }

        when {
            paymentRequest.securityCode.isEmpty()
                    || !paymentRequest.securityCode.isValidCVVLength() -> {
                isFormValid = isFormValid && false
                view.displayCardCvvError()
            }
            else -> {
                isFormValid = isFormValid && true
                view.hideCardCvvError()
            }
        }

        if (isFormValid) {
            performCheckout(paymentRequest)
        }

    }

    private fun performCheckout(paymentRequest: PaymentRequest) {
        view.displayProgressBar()
        paymentRepository.checkout(paymentRequest,
                { onSuccessCheckout(it) },
                { onErrorCheckout(it) })
    }

    private fun onSuccessCheckout(transaction: PaymentTransaction) {
        view.hideProgressBar()
        paymentRepository.saveTransactionLocally(transaction) { removeItemsFromCart() }
    }

    private fun removeItemsFromCart() {
        itemListRepository.removeItems { view.returnToStore() }
    }

    private fun onErrorCheckout(error: String) {
        view.hideProgressBar()
    }

}
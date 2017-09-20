package io.hasegawa.stonesafio.screen_transactions

import android.text.format.DateUtils
import android.widget.TextView
import com.airbnb.epoxy.TypedEpoxyController
import io.hasegawa.presentation.screen_transactions.Transaction
import io.hasegawa.stonesafio.R
import io.hasegawa.stonesafio.common.EpoxyKotterHolder
import io.hasegawa.stonesafio.common.EpoxyModelWithHolderKt
import io.hasegawa.stonesafio.common.bindView

class ListingRvTransactionModel(val transaction: Transaction)
    : EpoxyModelWithHolderKt<ListingRvTransactionModel.Holder>(
        R.layout.item_transactions_transaction, transaction.id) {

    override fun equals(other: Any?): Boolean =
            transaction == (other as? ListingRvTransactionModel)?.transaction

    override fun hashCode(): Int = transaction.hashCode()

    override fun bind(holder: Holder?) {
        holder?.apply {
            transaction.run {
                val date = DateUtils.formatDateTime(valueTv.context, instant,
                        DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_SHOW_DATE)
                valueTv.text = "R\$$value" // TODO[hase] proper value formatting
                timeTv.text = date
                cc4DigitsTv.text = ccLast4Digits
                ccNameTv.text = ccName
            }
        }
    }

    class Holder : EpoxyKotterHolder() {
        val valueTv: TextView by bindView(R.id.transaction_value_tv)
        val timeTv: TextView by bindView(R.id.transaction_time_tv)
        val cc4DigitsTv: TextView by bindView(R.id.transaction_cc_4_digits_tv)
        val ccNameTv: TextView by bindView(R.id.transaction_cc_name_tv)
    }

    override fun createNewHolder(): Holder = Holder()
}


class TransactionsRvController : TypedEpoxyController<List<Transaction>>() {
    override fun buildModels(data: List<Transaction>?) {
        data
                ?.map { ListingRvTransactionModel(it) }
                ?.onEach { add(it) }
    }
}

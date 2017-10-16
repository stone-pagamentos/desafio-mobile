package personal.pedrofigueiredo.milleniumstore.adapters

import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import personal.pedrofigueiredo.milleniumstore.R
import personal.pedrofigueiredo.milleniumstore.data.Product
import personal.pedrofigueiredo.milleniumstore.data.ShoppingCart

class CartListAdapter(private var activity: AppCompatActivity, private var cart: ShoppingCart?) : BaseAdapter() {
    private val items: ArrayList<Product>
        get() = cart?.getProductsAsList() as ArrayList<Product>


    class ViewHolder(row: View?) {
        var txtName: TextView? = null
        var txtQuantity: TextView? = null
        var txtSubtotal: TextView? = null

        init {
            txtName = row?.findViewById(R.id.txtProductName)
            txtQuantity = row?.findViewById(R.id.txtProductQuantity)
            txtSubtotal = row?.findViewById(R.id.txtProductSubtotal)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity.layoutInflater as LayoutInflater
            view = inflater.inflate(R.layout.cart_list_view, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val product = items[position]
        val quantity = cart?.getQuantityByProduct(product)
        val subtotal = product.price * quantity!!
        val res = activity.resources

        viewHolder.txtName?.text = product.title
        viewHolder.txtQuantity?.text = String.format(res.getString(R.string.cart_item_quantity), quantity.toString())
        viewHolder.txtSubtotal?.text = String.format(res.getString(R.string.cart_item_subtotal), subtotal.toString())


        return view as View
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Product = items[position]

    override fun getItemId(position: Int): Long = position.toLong()
}
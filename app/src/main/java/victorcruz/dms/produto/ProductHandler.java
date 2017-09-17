package victorcruz.dms.produto;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import victorcruz.dms.R;
import victorcruz.dms.UI.ExpandableHeightListView;


public class ProductHandler {

    private ArrayList<Product> productsStore;

    private ArrayList<Product> productsCart;
    private ProductCartAdapter productsCartAdapter;

    private ExpandableHeightListView cartListView;

    private TextView cartTotalValueTextView;
    private int cartTotalValue = 0;

    // for Toast
    private Activity act;

    public ProductHandler(ArrayList<Product> productsStore, ArrayList<Product> productsCart,
                          ProductCartAdapter productsCartAdapter, ExpandableHeightListView cartListView,
                          TextView cartTotalValueTextView, Activity act){
        this.productsStore = productsStore;
        this.productsCart = productsCart;
        this.productsCartAdapter = productsCartAdapter;
        this.cartListView = cartListView;
        this.cartTotalValueTextView = cartTotalValueTextView;
        this.act = act;
    }

    public void addToCart(View view){

        String title = "";
        int price = -1;
        String seller = "";

        // registra o titulo, preco e vendedor do produto nos campos acima
        TextView textView;
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        for (int itemPos = 0; itemPos < viewGroup.getChildCount(); itemPos++){
            View childView = viewGroup.getChildAt(itemPos);
            if (childView.getId() == R.id.title){
                textView = (TextView) childView;
                title = textView.getText().toString();
            } else
            if (childView.getId() == R.id.price){
                textView = (TextView) childView;
                price = Integer.parseInt(textView.getText().toString().substring(3).replace(".",""));
            } else
            if (childView.getId() == R.id.seller){
                textView = (TextView) childView;
                seller = textView.getText().toString();
            }
        }

        // procura o produto registrado e coloca no carrinho
        for (int i = 0; i < productsStore.size(); i++){
            if (productsStore.get(i).getTitle().equals(title) &&
                    productsStore.get(i).getPrice() == price &&
                    productsStore.get(i).getSeller().equals(seller.substring(3))){

                productsCart.add(new Product(productsStore.get(i)));
                System.out.println(productsStore.get(i).getTitle());

                break;
            }
        }

        Toast.makeText(act, "Produto adicionado ao carrinho!", Toast.LENGTH_SHORT).show();

        // atualiza as views
        cartListView.setAdapter(productsCartAdapter);
        refreshCartTotalValue();

    }

    public void removeFromCart(View view){
        String title = "";
        int price = -1;
        String seller = "";

        // registra o titulo, preco e vendedor do produto nos campos acima
        TextView textView = null;
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        for (int itemPos = 0; itemPos < viewGroup.getChildCount(); itemPos++){
            View childView = viewGroup.getChildAt(itemPos);
            if (childView.getId() == R.id.title){
                textView = (TextView) childView;
                title = textView.getText().toString();
            } else
            if (childView.getId() == R.id.price){
                textView = (TextView) childView;
                price = Integer.parseInt(textView.getText().toString().substring(3).replace(".",""));
            } else
            if (childView.getId() == R.id.seller){
                textView = (TextView) childView;
                seller = textView.getText().toString();
            }
        }

        // procura o produto registrado e remove do carrinho
        for (int i = 0; i < productsCart.size(); i++){
            if (productsCart.get(i).getTitle().equals(title) &&
                    productsCart.get(i).getPrice() == price &&
                    productsCart.get(i).getSeller().equals(seller.substring(3))){

                System.out.println("Removido: " + productsStore.get(i).getTitle());
                productsCart.remove(i);
                break;
            }
        }

        Toast.makeText(act, "Produto removido do carrinho!", Toast.LENGTH_SHORT).show();

        // atualiza a view
        cartListView.setAdapter(productsCartAdapter);
        refreshCartTotalValue();

    }

    public void refreshCartTotalValue(){

        cartTotalValue = 0;

        for (int i = 0; i < productsCart.size(); i++){
            cartTotalValue += productsCart.get(i).getPrice();
        }

        if (cartTotalValue == 0){
            cartTotalValueTextView.setText("R$ 0");
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#,#####,00");
            String value = decimalFormat.format((double) cartTotalValue);
            value = "R$ " + value;
            cartTotalValueTextView.setText(value);
        }
    }

    public int getCartTotalValue(){
        return cartTotalValue;
    }

}

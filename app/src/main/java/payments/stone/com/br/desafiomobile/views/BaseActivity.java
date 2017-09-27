package payments.stone.com.br.desafiomobile.views;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import payments.stone.com.br.desafiomobile.R;
import payments.stone.com.br.desafiomobile.checkout.AddCartItemDialog;
import payments.stone.com.br.desafiomobile.checkout.CreditCardCheckoutActivity;

import payments.stone.com.br.desafiomobile.commons.Navigation;
import payments.stone.com.br.desafiomobile.details.DetailsActivity;
import payments.stone.com.br.desafiomobile.home.HomeActivity;
import payments.stone.com.br.desafiomobile.model.CartItem;
import payments.stone.com.br.desafiomobile.model.Product;

import static payments.stone.com.br.desafiomobile.details.DetailsActivity.KEY_DETAILS_PRODUCT_BUNDLE;

/**
 * Created by william.gouvea on 9/22/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements Navigation, BaseView {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_cart:
                startActivity(new Intent(this, CreditCardCheckoutActivity.class));

                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem search = menu.findItem(R.id.action_search);
        if (search != null) {
            search.setVisible(true);
        }

       MenuItem reset =  menu.findItem(R.id.action_reset_cart);
        if(reset!=null){
            reset.setVisible(false);
        }

        MenuItem cart =  menu.findItem(R.id.action_show_cart);
        if(cart!=null){
            cart.setVisible(true);
        }

        return true;
    }

    @Override
    public void whenGoToDetails(Product product) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(KEY_DETAILS_PRODUCT_BUNDLE, product);
        startActivity(intent);
    }

    @Override
    public void whenGoToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void showQuantityDialog(CartItem current, AddCartItemDialog.AddCartItemDialogListener listener){
        FragmentManager fm = getSupportFragmentManager();
        AddCartItemDialog addCartItemDialog = AddCartItemDialog.newInstance("Pick Quantity", current,listener);
        addCartItemDialog.show(fm, "fragment_add_cart_item_quantity");

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_cart_item_dialog, null);
        dialogBuilder.setView(dialogView);
    }

    @Override
    public Context context() {
        return this;
    }
}

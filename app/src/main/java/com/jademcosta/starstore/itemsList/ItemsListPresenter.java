package com.jademcosta.starstore.itemsList;


import android.content.Context;
import android.content.Intent;

import com.jademcosta.starstore.cart.CartActivity;
import com.jademcosta.starstore.entity.Item;
import com.jademcosta.starstore.transactionsList.TransactionsListActivity;

import java.util.List;

public class ItemsListPresenter implements ItemsListContract.Model.Presenter,
        ItemsListContract.View.Presenter {

    private ItemsListContract.View view;
    private ItemsListContract.Model model;

    @Override
    public void onCreate() {
        view.showLoading();
        view.hideList();
        view.hideErrorView();
        model.getItemsList();
    }

    public void setView(ItemsListContract.View view) {
        this.view = view;
    }

    @Override
    public void itemClicked(Item item) {
        model.addItemToCart(item);
        view.informItemAddedToCart();
    }

    public void setModel(ItemsListContract.Model model) {
        this.model = model;
    }

    @Override
    public void onItemsListFetchFailure() {
        view.hideLoading();
        view.hideList();
        view.showErrorView();
    }

    @Override
    public void onItemsListFetched(List<Item> items) {
        view.setListItems(items);
        view.showList();
        view.hideLoading();
        view.hideErrorView();
    }

    public void goToCartButtonClicked(Context context) {
        Intent intent = CartActivity.newIntent(context);
        context.startActivity(intent);
    }

    @Override
    public void navigateToTransactionsClicked(Context context) {
        context.startActivity(TransactionsListActivity.newIntent(context));
    }
}

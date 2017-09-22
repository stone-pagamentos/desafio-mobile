package payments.stone.com.br.desafiomobile.cart;

import java.util.Collections;
import java.util.List;

import payments.stone.com.br.desafiomobile.Product;

/**
 * Created by william.gouvea on 9/21/17.
 */

public class ProductsResponse {
    public List<Product> productList;

    public ProductsResponse(List<Product> productList) {
        this.productList = Collections.unmodifiableList(productList);
    }
}

package manager.method;

import manager.method.ISort;
import product.Product;
import storage.ProductList;

import java.util.ArrayList;

public class SortDecrease implements ISort {

    ProductList ListInstance = ProductList.getInstance();
    ArrayList<Product> productList = ListInstance.productList;

    @Override
    public void sort() {
        ArrayList<Product> products = productList;
        Product temp;
        int length = products.size();
        double a = 0, b = 0;
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++) {
                a = (products.get(i)).getPrice();
                b = (products.get(j)).getPrice();
                if (a < b){
                    temp = products.get(i);
                    products.set(i,products.get(j));
                    products.set(j,temp);
                }
            }
        }
        for (Product product : products){
            System.out.printf("||Category: %-10s ||Brand: %-18s ||Name: %-28s ||Price:%10s ||ID: %s \n",
                    product.getCategory(),
                    product.getBrand(),
                    product.getName(),
                    product.getPrice(),
                    product.getID());
            System.out.println();
        }
    }
}

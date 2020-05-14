package manage.method;

import product.Product;

import java.util.ArrayList;

public class SortDecrease implements ISort {

    ArrayList<Product> productList = new ArrayList<>();
    @Override
    public void sort() {
        Product temp;
        int length = productList.size();
        double a = 0, b = 0;
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++) {
                a = (productList.get(i)).getPrice();
                b = (productList.get(j)).getPrice();
                if (a < b){
                    temp = productList.get(i);
                    productList.set(i,productList.get(j));
                    productList.set(j,temp);
                }
            }
        }
        for (Product product : productList){
            System.out.println(product.getName() + " " + product.getID() + " " + product.getPrice());
        }
    }
}

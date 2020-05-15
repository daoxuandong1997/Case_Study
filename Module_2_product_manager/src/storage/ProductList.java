package storage;

import product.Product;

import java.util.ArrayList;

public class ProductList extends ArrayList<Product> {
    public static ArrayList<Product> productList;
    private static Storage storage = new Storage();

    private ProductList(){
        this.productList = new ArrayList<>();
    }
    private static volatile ProductList instance;
    public synchronized static ProductList getInstance(){
        if(instance == null){
            instance = new ProductList();
        }
        return instance;
    }
    public static boolean setListFromFile(String source){
        return storage.readFile(productList, source);
    }
    public static boolean updateFileFromList(String source) {
       return storage.updateFile(productList, source);
    }


}
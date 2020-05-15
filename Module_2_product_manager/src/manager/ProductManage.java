package manager;

import crawler.ProductCrawler;
import manager.method.ISort;
import manager.method.SortDecrease;
import manager.method.SortIncrease;
import product.PhoneProduct;
import product.Product;
import storage.ProductList;

import java.util.ArrayList;
import java.util.Scanner;


public class ProductManage implements IProductManager {
    Scanner scanner = new Scanner(System.in);

    public static final String FILE_SOURCE = "F:\\lap trinh\\Codegym\\IntelliJProject\\Case_Study\\Module_2_product_manager\\src\\storage\\Product.txt";
    public static final String FILE_CRAWLER = "F:\\lap trinh\\Codegym\\IntelliJProject\\Case_Study\\Module_2_product_manager\\src\\crawler\\ProductCraw.txt";

    ProductList listInstance = ProductList.getInstance();
    ArrayList<Product> productList = listInstance.productList;


    private static volatile ProductManage instance;

    private ProductManage() {
        ProductList.setListFromFile(FILE_SOURCE);
    }

    public static ProductManage getInstance() {
        if (instance == null) {
            instance = new ProductManage();
        }
        return instance;
    }


    //================================================================
    @Override
    public void addProduct(){
        String id = enterID();
        if (checkExistId(id)) {
            System.out.println("Product has exist, please enter again !");
            enterID();
        }

        String name = enterName();
        String brand = enterBrand();
        String category = enterCategory();
        int price = Integer.parseInt(enterPrice());
        add(category,brand,name,price,id);
    }

    private void add( String category, String brand, String name, int price,String id ) {
        Product product = new PhoneProduct(name, id, price, category, brand) {};
        product.setCategory(category);
        product.setID(id);
        product.setName(name);
        product.setPrice(price);
        product.setBrand(brand);
        productList.add(product);
    }

    //==============================================================
    @Override
    public void displayProduct() {
        if (productList.isEmpty()) {
            System.out.println("List is empty !");
        } else
            showProductList();
    }

    private void showProduct(Product product) {
        System.out.printf("||Category: %-10s ||Brand: %-18s ||Name: %-28s ||Price:%10s ||ID: %s \n",
                product.getCategory(),
                product.getBrand(),
                product.getName(),
                product.getPrice(),
                product.getID());
        System.out.println();
    }

    private void showProductList() {

        for (Product product : productList) {
            showProduct(product);
        }
    }

    //============================================================
    @Override
    public boolean editProduct() {
        String id = enterID();
        if (checkExistId(id)){
            editProductElement(id);
            return true;
        }
        return false;
    }

    private void editProductElement(String id) {
        Product editProduct = new PhoneProduct();
        for (int i = 0; i < productList.size(); i++){
            if (id.equals(productList.get(i).getID().trim())){
                editProduct = productList.get(i);
                break;
            }
        }

        String brand = enterBrand();
        String name = enterName();
        String category = enterCategory();
        int price = Integer.parseInt(enterPrice());

        editProduct.setCategory(category);
        editProduct.setName(name);
        editProduct.setPrice(price);
        editProduct.setBrand(brand);
    }


    //====================================
    @Override
    public boolean deleteProduct() {
        String id = enterID();
        if (checkExistId(id)){
            for (int i = 0; i < productList.size(); i++){
                if (id.equals(productList.get(i).getID().trim())) {
                    productList.remove(productList.get(i));
                    return true;
                }
            }
        }return false;
    }

    // =======================================================
    @Override
    public void searchProduct() {
        int choice = choice();
        final int SB_NAME = 1;
        final int SB_ID = 2;
        final int SB_BRAND = 3;
        final int SB_CATEGORY = 4;

        switch (choice){
            case SB_NAME :
                searchByName();
                break;
            case SB_ID :
                searchByID();
                break;
            case SB_BRAND :
                searchByBrand();
                break;
            case SB_CATEGORY :
                searchByCategory();
                break;
        }
    }

    private void searchByName() {
        String name = enterName();
        if (checkExistName(name)) {
            for ( int i = 0; i < productList.size(); i++) {
                    if ((name.trim()).equalsIgnoreCase(productList.get(i).getName().trim())) {
                        showProduct(productList.get(i));
                }
            }
        }else System.out.println("Không có sản phẩm tên " + name);
    }

    private void searchByID() {
        String ID = enterID();
        if (checkExistId(ID)) {
            for ( int i = 0; i < productList.size(); i++) {
                if ((ID.trim()).equalsIgnoreCase(productList.get(i).getID().trim())) {
                    showProduct(productList.get(i));
                }
            }
        }else System.out.println("Không có sản phẩm có ID là : " + ID);
    }

    private void searchByBrand() {
        String brand = enterBrand();
        if (checkExistBrand(brand)) {
            for ( int i = 0; i < productList.size(); i++) {
                if ((brand.trim()).equalsIgnoreCase(productList.get(i).getBrand().trim())) {
                    showProduct(productList.get(i));
                }
            }
        }else System.out.println("Không có sản phẩm có brand là : " + brand);
    }

    private void searchByCategory() {
        String category = enterCategory();
        if (checkExistCategory(category)) {
            for ( int i = 0; i < productList.size(); i++) {
                if ((category.trim()).equalsIgnoreCase(productList.get(i).getCategory().trim())) {
                    showProduct(productList.get(i));
                }
            }
        }else System.out.println("Không có sản phẩm " + category);
    }

//    private void searchByPrice() {
//        int price = Integer.parseInt(enterPrice());
//
//
//    }

    // ============================================================
    @Override
    public void sortProduct(){
        int choice = choice();
        final int INCREASING = 1;
        final int DECREASING = 2;
        switch (choice){
            case INCREASING :
                ISort increasingSort = new SortIncrease();
                sort(increasingSort);
                break;
            case DECREASING :
                ISort decreasingSort = new SortDecrease();
                sort(decreasingSort);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    private void sort(ISort sorter) {
        sorter.sort();
    }

    //=======================================================
    public void update(){
        ProductList.updateFileFromList(FILE_SOURCE);
    }

    //=========================================================
    public void crawling() {
        if (productList.size() == 0) {
            ProductCrawler CrawInstance = ProductCrawler.getInstance();
            CrawInstance.crawl(FILE_CRAWLER);
            ProductList.setListFromFile(FILE_CRAWLER);
        }
    }


    //==================================================
    //==================================================

    private String enterID(){
        System.out.println("Enter ID : ");
        String id = scanner.nextLine();
        return id;
    }

    private boolean checkExistId(String id) {
        for (int i = 0; i < productList.size(); i++) {
            if ((id.trim()).equals(productList.get(i).getID().trim()))
                return true;
        }
        return false;
    }

    //===============================
    private String enterName(){
        System.out.println("Enter phone name : ");
        String name = scanner.nextLine();
        return name;
    }

    private boolean checkExistName(String name) {
        for (int i = 0; i < productList.size(); i++) {
            if ((name.trim()).equals(productList.get(i).getName().trim()))
                return true;
        }
        return false;
    }
    //======================================
    private String enterBrand(){
        System.out.println("Enter brand : ");
        String brand = scanner.nextLine();
        return brand;
    }

    private boolean checkExistBrand(String brand) {
        for (int i = 0; i < productList.size(); i++) {
            if ((brand.trim()).equals(productList.get(i).getBrand().trim()))
                return true;
        }
        return false;
    }

    //======================================
    private String enterCategory(){
        System.out.println("Enter category : ");
        String category = scanner.nextLine();
        return category;
    }

    private boolean checkExistCategory(String category) {
        for (int i = 0; i < productList.size(); i++) {
            if ((category.trim()).equals(productList.get(i).getCategory().trim()))
                return true;
        }
        return false;
    }

    //======================================
    private String enterPrice(){
        System.out.println("Enter price : ");
        String price = scanner.nextLine();
        return price;
    }

    //===================================
    private int choice(){
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }
//    private boolean checkExistPrice(String price) {
//        for (int i = 0; i < productList.size(); i++) {
//            if ((price.trim()).equals(productList.get(i).getBrand().trim()))
//                return true;
//        }
//        return false;
//    }

}

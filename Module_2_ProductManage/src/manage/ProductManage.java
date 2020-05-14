package manage;
import manage.method.ISort;
import product.Product;
import storage.ProductList;
import java.util.ArrayList;
import java.util.Scanner;


public class ProductManage implements IProductManager {
    Scanner scanner = new Scanner(System.in);

    ProductList listInstance = ProductList.getInstance();
    ArrayList<Product> productsList = listInstance.productList;

    private static volatile ProductManage instance;

    private ProductManage() {
    }

    public static ProductManage getInstance() {
        if (instance == null){
            instance = new ProductManage();
        }
        return instance;
    }

    public boolean checkExistId(String id) {
        boolean result = false;
        for (int i = 0; i < productsList.size(); i++) {
            if (id.equals(productsList.get(i).getID())) {
                result = true;
            }
        }
        return result;
    }

    public boolean addProduct(String id, String name, int price,String category, String description){
        Product product = new Product(name,id,price,category,description){};
        product.setCategory(category);
        product.setID(id);
        product.setName(name);
        product.setPrice(price);
        product.setDescreption(description);
        productsList.add(product);
        return true;
    }

    public boolean showProduct() {
        if (productsList.isEmpty()){
            System.out.println("List is empty !");
            return false;
        } else {
            showProductList();
        }
        return true;
    }

    private void showProductList() {

        for (Product product : productsList) {
            System.out.println("Brand : " + product.getName()
                    + "\nID : " + product.getID()
                    + "\nPrice : " + product.getPrice()
                    + "\nCategory : " + product.getCategory()
                    + "\nPhoneCode : " + product.getDescreption());
            System.out.println("================================");

        }
    }

    public boolean editProduct(String ID) {
        for (Product product : productsList){
            if (ID.equals(product.getID())){
                editProductElement(product);
                return true;
            }
        }
        return false;
    }

    private void editProductElement(Product product){
        System.out.println("Enter new name : ");
        String newName = scanner.nextLine();
        product.setName(newName);
        System.out.println("Enter new price : ");
        String newPrice = scanner.next();
        product.setPrice(Integer.parseInt(newPrice));
        System.out.println("Enter new description : ");
        String newDescription = scanner.nextLine();
        product.setName(newDescription);

    }

    public void deleteProduct(String ID) {
        for (Product product : productsList){
            if (ID.equals(product.getID())){
                productsList.remove(product);
                break;
            }
        }
    }

    public void searchProduct(String name) {
        for (Product product : productsList){
            if (name.equals(product.getName())){
                System.out.println("ArrayList.Product need search : " + product.getID());
            }
        }
    }

    public void sortProduct(ISort sorter) {
        sorter.sort();
    }
}


import manager.ProductManage;

import java.util.Scanner;

public class Main {

    public static final int CRAWL = 1;
    public static final int SHOW = 2;
    public static final int ADD = 3;
    public static final int DELETE = 4;
    public static final int EDIT = 5;
    public static final int SEARCH = 6;
    public static final int SORT = 7;
    public static final int SAVE = 8;
    public static final int EXIT = 0;

    public  static void showMenu(){
        System.out.println("Menu");
        System.out.println("1. Crawl from thegioididong.com");
        System.out.println("2. Show product list");
        System.out.println("3. Add product");
        System.out.println("4. Delete product");
        System.out.println("5. Edit product ");
        System.out.println("6. Search product ");
        System.out.println("7. Sort product list");
        System.out.println("8. Save");
        System.out.println("0. Exit");
        System.out.println("Please input the number : ");
    }

    public static void main(String[] args) throws Exception {
        ProductManage productManage = ProductManage.getInstance();
//        IProductFactory productFactory = new ProductFactory();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case CRAWL:
                    productManage.crawling();
                    System.out.println("list đã có sản phẩm");
                    break;
                case SHOW:
                    productManage.displayProduct();
                    productManage.update();
                    break;
                case ADD :
                    productManage.addProduct();
                    System.out.println("Sản phẩm đã được thêm vào trong danh sách");
                    productManage.update();
                    break;
                case DELETE:
                    if(productManage.deleteProduct()) System.out.println("đã xóa");
                    else System.out.println("không có sản phẩm trong danh sách");
                    productManage.update();
                    break;
                case EDIT:
                    if(productManage.editProduct()) System.out.println("đã edit");
                    else System.out.println("không có sản phẩm trong danh sách");
                    productManage.update();
                    break;
                case SEARCH:
                    System.out.println("1. Search by name of product ");
                    System.out.println("2. Search by ID of product ");
                    System.out.println("3. Search by brand of product ");
                    System.out.println("4. Search by category of product ");
                    System.out.println("Please enter your choice ");
                    productManage.searchProduct();
                    break;
                case SORT:
                    System.out.println("1. Sort by price of product follow increasing ");
                    System.out.println("2. Sort by price of product follow decreasing ");
                    System.out.println("Please enter your choice ");
                    productManage.sortProduct();
                    break;
                case SAVE :
                    productManage.update();
                    break;
                case EXIT:
                    System.exit(0);
                default:
                    throw new Exception("this choice isn't exist");
            }
        }while (choice != 0);
    }
}

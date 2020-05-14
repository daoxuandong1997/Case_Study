import manage.ProductManage;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public  static void showMenu(){
        System.out.println("Menu");
        System.out.println("1. Show product list");
        System.out.println("2. Add product");
        System.out.println("3. Edit product follow the name");
        System.out.println("4. Delete product ");
        System.out.println("5. Search product ");
        System.out.println("6. Sort product list");
        System.out.println("0. Exit");
        System.out.println("Please input the number : ");
    }

    public static void main(String[] args) throws FileNotFoundException {
        ProductManage productManage = ProductManage.getInstance();
//        IProductFactory productFactory = new ProductFactory();
        Scanner scanner = new Scanner(System.in);
        int choice;
         do {
             showMenu();
             choice = scanner.nextInt();
             switch (choice) {
                case 1:
                    productManage.showProduct();
                    break;
                case 2:
                    String id;
                    do{
                        System.out.println("Enter ID : ");
                        id = scanner.next();
                    }while (productManage.checkExistId(id));
                    System.out.println("Enter name : ");
                    String name = scanner.next();
                    System.out.println("Enter price : ");
                    String price = scanner.next();
                    System.out.println("Enter category : ");
                    String category = scanner.next();
                    System.out.println("Enter description : ");
                    String description = scanner.next();
                    productManage.addProduct(id,name,Integer.parseInt(price),category,description);
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    System.exit(0);
            }
        }while (choice != 0);
    }
}

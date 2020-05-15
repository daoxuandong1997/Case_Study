package manager;

import manager.method.ISort;

public interface IProductManager {
    public void addProduct();

    public void displayProduct();

    public boolean editProduct();

    public boolean deleteProduct();

    public void searchProduct() ;

    void sortProduct();

}

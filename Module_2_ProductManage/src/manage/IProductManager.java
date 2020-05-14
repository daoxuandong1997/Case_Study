package manage;

import manage.method.ISort;

public interface IProductManager {
    public boolean addProduct(String id, String name, int price,String category, String description);

    public boolean showProduct();

    public boolean editProduct(String ID);

    public void deleteProduct(String name);

    public void searchProduct(String name) ;

    public void sortProduct(ISort sorter);
}

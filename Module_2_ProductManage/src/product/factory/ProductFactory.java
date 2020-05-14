package product.factory;

import jdk.jshell.spi.ExecutionControl;
import product.Product;

public class ProductFactory implements IProductFactory {

    @Override
    public Product createProduct(ProductTypes productTypes) throws ExecutionControl.NotImplementedException {
        switch (productTypes) {
            case FOOD:
                return new IphoneProduct();
            case ELECTRONIC:
                return new SamSungProduct();
            default:
                throw new ExecutionControl.NotImplementedException("None");
        }
    }
}

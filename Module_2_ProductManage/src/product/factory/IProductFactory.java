package product.factory;

import jdk.jshell.spi.ExecutionControl;
import product.Product;

public interface  IProductFactory {
    Product createProduct(ProductTypes productTypes) throws ExecutionControl.NotImplementedException;
}

package ar.com.factorit.ecommerce.product;

public class ProductNotExistException extends Exception {
    public ProductNotExistException() {
        super("Product does not exist");
    }
}

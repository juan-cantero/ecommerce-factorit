package ar.com.factorit.ecommerce.category;

public class CategoryNotExistException extends IllegalArgumentException{
    public CategoryNotExistException() {
        super();
    }

    public CategoryNotExistException(String s) {
        super(s);
    }
}

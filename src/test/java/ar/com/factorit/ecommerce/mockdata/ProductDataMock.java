package ar.com.factorit.ecommerce.mockdata;

import ar.com.factorit.ecommerce.category.Category;
import ar.com.factorit.ecommerce.product.Product;

import java.math.BigDecimal;

public class ProductDataMock {
    public static Product getPcGamer() {
        return new Product(1,"compu gamer","compugamer.jpg",
                BigDecimal.valueOf(200000),
                "computadora gamer",
                new Category());
    }

    public static Product getSmartTv() {
        return new Product(2,"smart tv","smarttv.jpg",
                BigDecimal.valueOf(40000),
                "smart tv",
                new Category());
    }

    public static Product getCocaCola() {
        return new Product(3,"coca 2lt","coca.jpg",
                BigDecimal.valueOf(150),
                "coca cola 2lt",
                new Category());
    }
}

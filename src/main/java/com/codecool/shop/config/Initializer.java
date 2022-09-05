package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
//        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory techno = new ProductCategory("Techno", "Vinyl", "Techno is a genre of electronic dance music (EDM) which is generally produced for use in a continuous DJ set, with tempo often varying between 120 and 150 beats per minute (bpm).");
        ProductCategory house = new ProductCategory("House", "Vinyl", "House is a genre of electronic dance music characterized by a repetitive four-on-the-floor beat and a typical tempo of 115 to 130 beats per minute.");
        ProductCategory electronic = new ProductCategory("Electronic", "Vinyl", "electronic music, any music involving electronic processing, such as recording and editing on tape, and whose reproduction involves the use of loudspeakers.");
        ProductCategory rock = new ProductCategory("Rock", "Vinyl", "Rock music, also called rock and roll, rock & roll, or rock 'n' roll, is a form of popular music that emerged in the 1950s and is defined as “a form of music with a strong beat”—it is difficult to be much more explicit about it.");
        productCategoryDataStore.add(techno);
        productCategoryDataStore.add(house);
        productCategoryDataStore.add(electronic);
        productCategoryDataStore.add(rock);

        //setting up products and printing it
        productDataStore.add(new Product("I Hate Models -\n Intergalactic Emotional Breakdown", new BigDecimal("25.99"), "EUR", " ", techno, amazon));
        productDataStore.add(new Product("Four Tet -\n There Is Love In You", new BigDecimal("28.99"), "EUR", " ", house, amazon));
        productDataStore.add(new Product("Floating Points -\n Crush", new BigDecimal("30.00"), "EUR", " ",  electronic, amazon));
        productDataStore.add(new Product("Rammstein -\n  Deutschland", new BigDecimal("22.99"), "EUR", " ",  rock, amazon));
        productDataStore.add(new Product("Nils Frahm -\n  All Melody", new BigDecimal("28.99"), "EUR", " ",  electronic, amazon));
        productDataStore.add(new Product("Floating Points & Pharoah Sanders -\n  Promises", new BigDecimal("34.00"), "EUR", " ",  electronic, amazon));
        productDataStore.add(new Product("Leon Vynehall -\n Music For The Uninvited", new BigDecimal("14.00"), "EUR", " ",  house, amazon));
    }
}

package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        //create Product
        Product product = new Product();
        product.setName("One Plus 11");
        product.setDescription("Snapdragon 8 Gen 2 based One Plus 11");
        product.setSku("100OnePlus11abc");
        product.setPrice(new BigDecimal(61000.00));
        product.setActive(true);
        product.setImageURl("OnePlus11.png");

        //saveProduct
        Product savedProduct = productRepository.save(product);

        //displayProductInfo
        System.out.println(savedProduct.toString());
    }

    @Test
    void updateUsingSaveMethod(){

        //find or retrieve entity by ID
        Long id = 3L;
        Product product = productRepository.findById(id).get();

        //update entity information
        product.setImageURl("/OnePlus 11R 5G.png");
        product.setDescription("Snapdragon 8+ Gen 1 based One Plus 11R 5G");

        //save updated entity
        productRepository.save(product); // here it will use merge method of Entity Manager
    }

    @Test
    void findByMethod(){
        Long id = 1L;
        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod(){

        //create Product

        Product product0 = Product.builder()
                .name("One Plus 11")
                .description("Snapdragon 8 Gen 2 based One Plus 11")
                .imageURl("/OnePlus11.png")
                .sku("100OnePlus11abc")
                .price(new BigDecimal("61000.00"))
                .active(true)
                .build();

        //create Product
        Product product1 = Product.builder()
                .name("One Plus 11R 5G")
                .description("Snapdragon 8+ Gen 1 based One Plus 11 5G")
                .imageURl("/OnePLus 11R %G.png")
                .sku("100OnePlus11R")
                .price(new BigDecimal("44000.00"))
                .active(true)
                .build();

        Product product2 = Product.builder()
                .name("One Plus Buds Pro 2")
                .description("One PLus Buds Pro 2 with 48dB Adaptive Noise Cancellation")
                .imageURl("/OnePlus Buds Pro 2.png")
                .sku("100Buds2")
                .price(new BigDecimal("12000.00"))
                .active(true)
                .build();

        Product product3 = Product.builder()
                .name("Samsung Galaxy S23 Ultra 5G")
                .description("Samsung Galaxy S23 Ultra 5G (Phantom Black, 12GB, 512GB Storage)")
                .imageURl("/Samsung S 23 Ultra.png")
                .sku("12512S23ULTRA")
                .price(new BigDecimal("134000.00"))
                .active(true)
                .build();

        Product product4 = Product.builder()
                .name("Samsung Galaxy S23 5G")
                .description("Samsung Galaxy S23 5G (Phantom Black, 8GB, 256GB Storage)")
                .imageURl("/Samsung S23.png")
                .sku("8256S23")
                .price(new BigDecimal("79999.00"))
                .active(true)
                .build();

        Product product5 = Product.builder()
                .name("Apple Iphone 14 Pro Max")
                .description("Apple iPhone 14 Pro Max 256GB Deep Purple")
                .imageURl("/Iphone 14 Pro Max.png")
                .sku("8256I14PM")
                .price(new BigDecimal("149000.00"))
                .active(true)
                .build();

        Product product6 = Product.builder()
                .name("Apple Iphone 14 Pro")
                .description("Apple iPhone 14 Pro 256 GB Deep Purple")
                .imageURl("/Iphone 14 Pro.png")
                .sku("8256I14P")
                .price(new BigDecimal("129000.00"))
                .active(true)
                .build();

        Product product7 = Product.builder()
                .name("Apple Iphone 14 Plus")
                .description("Apple iPhone 14 Plus 128GB Blue")
                .imageURl("/Iphone 14 Plus.png")
                .sku("8128I14PL")
                .price(new BigDecimal("90999.00"))
                .active(true)
                .build();

        Product product8 = Product.builder()
                .name("Apple Iphone 14")
                .description("Apple iPhone 14 256 GB Deep Purple")
                .imageURl("/Iphone 14.png")
                .sku("8256I14")
                .price(new BigDecimal("80999.00"))
                .active(true)
                .build();


        //saving all products
        List<Product> productList = productRepository.saveAll(
                List.of(
                        product0,product1,product2,product3,product4,
                        product5, product6, product7,product8
                )
        );

        productList.forEach(System.out::println);
    }

    @Test
    void findAllMethod(){

        List<Product> products = productRepository.findAll();
        System.out.println("\nRetrieved Products are:\n");
        products.forEach(product -> System.out.println(product.toString()));
        System.out.println();
    }

    @Test
    void deleteByIdMethod(){
        Long Id = 1L;
        productRepository.deleteById(Id);
    }

    @Test
    void deleteMethod(){
        //find entity by Id
        Long Id = 1L;
        Optional<Product> productOptional = productRepository.findById(Id);
        if(productOptional.isEmpty())
            return;

        //deleteEntity
        productRepository.delete(productOptional.get());
    }

    @Test
    void deleteAllMethod(){
        //productRepository.deleteAll();

        Product product = productRepository.findById(9L).get();
        Product product1 = productRepository.findById(10L).get();

        productRepository.deleteAll(List.of(product1, product));
    }

    @Test
    void countMethod(){
        long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsByIdMethod(){
        Long id = 7L;
        boolean result = productRepository.existsById(id);
        System.out.println(result);
    }
}
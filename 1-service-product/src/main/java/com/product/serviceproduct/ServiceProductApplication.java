package com.product.serviceproduct;

import com.product.serviceproduct.entities.Product;
import com.product.serviceproduct.repositories.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(ProductRepo ProductRepo){
        return args -> {
                Product product = Product.builder().name("PC").description("Pc DELL").prix(8000).prixUsine(50000).stock(20)
                                .build();
                ProductRepo.save(product);

        };
    }
}

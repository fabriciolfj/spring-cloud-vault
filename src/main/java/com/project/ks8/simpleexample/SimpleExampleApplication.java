package com.project.ks8.simpleexample;

import com.project.ks8.simpleexample.domain.model.Product;
import com.project.ks8.simpleexample.domain.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.ks8.simpleexample")
@EntityScan(basePackageClasses = Product.class)
@EnableJpaRepositories(basePackageClasses = ProductRepository.class)
public class SimpleExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleExampleApplication.class, args);
	}

}

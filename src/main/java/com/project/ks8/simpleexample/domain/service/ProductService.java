package com.project.ks8.simpleexample.domain.service;

import com.project.ks8.simpleexample.domain.exception.ProductNotFound;
import com.project.ks8.simpleexample.domain.model.Product;
import com.project.ks8.simpleexample.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> findAll() {
        log.info("Realizando pesquisa de todos os produtos.");
        delay();
        return repository.findAll();
    }

    public Product findlById(final Long id) {
        log.info("Realizando pesquisa do produto: " + id);
        return repository.findById(id).orElseThrow(() -> new ProductNotFound("Product not found."));
    }

    public Product update(final Long id, final Product product) {
        log.info("Atualizando o produto: " + id + ", informações: " + product.toString());
        return repository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(product, entity, "id");
                    return repository.save(entity);
                }).orElseThrow(() -> new ProductNotFound("Product not found"));
    }

    public void delete(final Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {}
    }

    public Product create(final Product product) {
        return repository.save(product);
    }

    private void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

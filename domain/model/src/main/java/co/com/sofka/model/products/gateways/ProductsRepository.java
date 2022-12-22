package co.com.sofka.model.products.gateways;

import co.com.sofka.model.products.Products;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductsRepository {
    Flux<Products> findAll();
    Mono<Products> save(Products products);
    Mono<Products> findById(String id);
    Mono<Void> deleteById(String id);
}

package co.com.sofka.usecase.listproducts;

import co.com.sofka.model.products.Products;
import co.com.sofka.model.products.gateways.ProductsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ListProductsUseCase {
    private final ProductsRepository productsRepository;
    public Flux<Products> ListProduct(){
        return  productsRepository.findAll();
    }
}

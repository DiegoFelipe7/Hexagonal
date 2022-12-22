package co.com.sofka.usecase.productid;

import co.com.sofka.model.products.Products;
import co.com.sofka.model.products.gateways.ProductsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class ProductIdUseCase implements Function<String , Mono<Products>> {
    private final ProductsRepository productsRepository;
    @Override
    public Mono<Products> apply(String id) {
        return productsRepository.findById(id);
    }
}

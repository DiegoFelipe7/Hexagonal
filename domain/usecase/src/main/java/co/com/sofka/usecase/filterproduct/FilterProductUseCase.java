package co.com.sofka.usecase.filterproduct;

import co.com.sofka.model.products.Products;
import co.com.sofka.model.products.gateways.ProductsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class FilterProductUseCase implements Function<String , Flux<Products>> {
    private final ProductsRepository productsRepository;
    @Override
    public Flux<Products> apply(String name) {
        return productsRepository.findAll()
                .filter(ele->ele.getName().contains(name))
                .switchIfEmpty(Flux.error(new NullPointerException("Not data")));
    }
}

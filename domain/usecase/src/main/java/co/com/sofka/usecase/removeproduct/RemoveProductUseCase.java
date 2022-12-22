package co.com.sofka.usecase.removeproduct;

import co.com.sofka.model.products.gateways.ProductsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class RemoveProductUseCase implements Function<String, Mono<Void>> {
    private final ProductsRepository productsRepository;
    @Override
    public Mono<Void> apply(String id) {
        return productsRepository.deleteById(id);
    }
}

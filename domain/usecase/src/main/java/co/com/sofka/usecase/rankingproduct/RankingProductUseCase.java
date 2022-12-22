package co.com.sofka.usecase.rankingproduct;

import co.com.sofka.model.products.Products;
import co.com.sofka.model.products.gateways.ProductsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class RankingProductUseCase implements Function<Double , Flux<Products>> {
    private final ProductsRepository productsRepository;
    @Override
    public Flux<Products> apply(Double price) {
        return productsRepository.findAll()
                .filter(ele->ele.getPrice()>price);
    }
}

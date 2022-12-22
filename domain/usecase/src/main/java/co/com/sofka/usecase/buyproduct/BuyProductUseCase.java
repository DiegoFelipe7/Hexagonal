package co.com.sofka.usecase.buyproduct;

import co.com.sofka.model.products.Products;
import co.com.sofka.model.products.gateways.ProductsRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class BuyProductUseCase implements Function<List<Products>, Flux<List<Products>>> {
    private final ProductsRepository productsRepository;

    @Override
    public Flux<List<Products>> apply( List<Products> products) {
        return Flux.fromIterable(products).flatMap(pro ->
                productsRepository.findAll()
                        .filter(ele -> ele.getId().equalsIgnoreCase(pro.getId()))
                        .flatMap(data -> {
                            int i = data.getAmount() - pro.getAmount();
                            data.setAmount(i);
                            return productsRepository.save(data);
                        }).map(ele -> {
                            ele.setAmount(pro.getAmount());
                            ele.setPrice(ele.getPrice() * pro.getAmount());
                            return ele;
                        }).collectList());
    }
}

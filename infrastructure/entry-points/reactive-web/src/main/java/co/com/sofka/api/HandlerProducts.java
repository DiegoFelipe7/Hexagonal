package co.com.sofka.api;

import co.com.sofka.model.products.Products;
import co.com.sofka.usecase.buyproduct.BuyProductUseCase;
import co.com.sofka.usecase.filterproduct.FilterProductUseCase;
import co.com.sofka.usecase.listproducts.ListProductsUseCase;
import co.com.sofka.usecase.productid.ProductIdUseCase;
import co.com.sofka.usecase.removeproduct.RemoveProductUseCase;
import co.com.sofka.usecase.saveproduct.SaveProductUseCase;
import co.com.sofka.usecase.updateproduct.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HandlerProducts {
    private final ListProductsUseCase listProductsUseCase;
    private final FilterProductUseCase filterProductUseCase;
    private final SaveProductUseCase saveProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final RemoveProductUseCase removeProductUseCase;
    private final ProductIdUseCase productIdUseCase;

    private final BuyProductUseCase buyProductUseCase;

    public Mono<ServerResponse> ListProducts(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(listProductsUseCase.ListProduct(), Products.class)
                .switchIfEmpty(Mono.error(new NullPointerException("No hay data")));
    }

    public Mono<ServerResponse> Product(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productIdUseCase.apply(id), Products.class)
                .switchIfEmpty(Mono.error(new NullPointerException("No hay data")));
    }

    public Mono<ServerResponse> FilterProducts(ServerRequest serverRequest) {
        String name = serverRequest.pathVariable("name");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(filterProductUseCase.apply(name), Products.class);
    }

    public Mono<ServerResponse> SaveProducts(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Products.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(saveProductUseCase.apply(ele), Products.class));
    }

    public Mono<ServerResponse> editProducts(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest
                .bodyToMono(Products.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateProductUseCase.apply(id, ele), Products.class));
    }

    public Mono<ServerResponse> deleteProducts(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(removeProductUseCase.apply(id), Products.class);

    }

    public Mono<ServerResponse> buyProduct(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(List.class)
                .flatMap(ele ->
                        ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(buyProductUseCase.apply(ele), List.class));
    }
}



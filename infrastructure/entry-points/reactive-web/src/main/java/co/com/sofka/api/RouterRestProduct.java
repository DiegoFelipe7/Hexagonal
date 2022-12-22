package co.com.sofka.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRestProduct {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(HandlerProducts handlerProducts) {
        return route(GET("/api/products"), handlerProducts::ListProducts)
                .andRoute(GET("/api/products/{id}"), handlerProducts::Product)
                .andRoute(POST("/api/products/buy"), handlerProducts::buyProduct)
                .andRoute(GET("/api/products/filter/{name}"), handlerProducts::FilterProducts)
                .andRoute(POST("/api/products"), handlerProducts::SaveProducts)
                .andRoute(POST("/api/products/{id}"), handlerProducts::editProducts)
                .andRoute(DELETE("/api/products/remove/{id}"), handlerProducts::deleteProducts);


    }
}

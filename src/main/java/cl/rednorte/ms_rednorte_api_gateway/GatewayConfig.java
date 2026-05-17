package cl.rednorte.ms_rednorte_api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> customRoutes() {
        return route("ruta-agenda")
                .route(path("/agendas/**"), http())
                .before(uri("http://ms-agenda-profesional:8080"))
                .build()
                .and(route("ruta-ficha")
                        .route(path("/api/v1/**"), http())
                        .before(uri("http://ms-ficha-clinica:8001"))
                        .build())
                .and(route("ruta-centros")
                        .route(path("/centros/**"), http())
                        .before(uri("http://rednorte-ms-centros:8080"))
                        .build())
                .and(route("ruta-urgencias")
                        .route(path("/urgencias/**"), http())
                        .before(uri("http://ms-urgencias-flujo:8080"))
                        .build());
    }
}

package com.api.articles.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class WebConfig: WebFluxConfigurer
{
    override fun addCorsMappings(registry: CorsRegistry)
    {
        registry.addMapping("/**")
                .allowedOrigins("*") // allowing access from any domain
                .allowedMethods("GET") // allowing get http verb
    }
}
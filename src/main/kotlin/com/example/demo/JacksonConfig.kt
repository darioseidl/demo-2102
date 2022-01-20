package com.example.demo

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.jackson.datatype.money.MoneyModule

@Configuration
class JacksonConfig {

    @Bean
    fun kotlinModule(): Module =
        KotlinModule()

    @Bean
    fun moneyModule(): Module =
        MoneyModule()

    @Bean
    @ConditionalOnProperty("use-custom-domain-object-reader")
    fun domainObjectReaderCustomizer() =
        DomainObjectReaderCustomizer()
}

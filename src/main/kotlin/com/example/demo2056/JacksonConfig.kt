package com.example.demo2056

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.module.kotlin.KotlinModule
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

}

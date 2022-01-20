package com.example.demo2056

import org.javamoney.moneta.Money
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service
import javax.money.Monetary

@Service
class ExampleService(
    private val exampleRepository: ExampleRepository
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        exampleRepository.save(
            Example(Money.of(123, Monetary.getCurrency("EUR")))
        )
    }
}

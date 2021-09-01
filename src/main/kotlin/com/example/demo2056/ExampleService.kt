package com.example.demo2056

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service

@Service
class ExampleService(
    private val exampleRepository: ExampleRepository
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        exampleRepository.save(Example())
    }
}

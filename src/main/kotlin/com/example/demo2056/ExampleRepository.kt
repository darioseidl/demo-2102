package com.example.demo2056

import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "examples", path = "examples")
interface ExampleRepository : CrudRepository<Example, Long>

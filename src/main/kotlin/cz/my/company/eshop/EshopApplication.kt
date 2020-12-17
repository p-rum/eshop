package cz.my.company.eshop


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableFeignClients
@SpringBootApplication
@EnableMongoRepositories
class EshopApplication

fun main(args: Array<String>) {
  runApplication<EshopApplication>(*args)

}
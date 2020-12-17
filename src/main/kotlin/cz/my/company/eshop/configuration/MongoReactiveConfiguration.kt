package cz.my.company.eshop.configuration

import cz.my.company.eshop.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(
    basePackageClasses = arrayOf(UserRepository::class))
class MongoReactiveConfiguration : AbstractReactiveMongoConfiguration() {

  override fun getDatabaseName() = "mongoDatabase"

  override fun reactiveMongoClient() = mongoClient()

  @Bean
  fun mongoClient() = MongoClients.create()

  @Bean
  override fun reactiveMongoTemplate(@Qualifier("reactiveMongoDatabaseFactory") databaseFactory: ReactiveMongoDatabaseFactory, mongoConverter: MappingMongoConverter): ReactiveMongoTemplate = ReactiveMongoTemplate(mongoClient(), databaseName)
}
package cz.my.company.eshop.repository

import cz.my.company.eshop.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository : ReactiveMongoRepository<User, ObjectId> {
}
package order.service.repository

import io.micronaut.transaction.annotation.ReadOnly
import order.service.domain.Order
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Singleton
open class OrderRepositoryImpl(
        @field:PersistenceContext val entityManager: EntityManager
) : OrderRepository {
    
    @ReadOnly
    override fun findById(id: Long): Order? = entityManager.find(Order::class.java, id)

    @Transactional
    override fun save(order: Order) = entityManager.persist(order)
}
package order.service.repository

import io.micronaut.transaction.annotation.ReadOnly
import order.service.domain.Customer
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Singleton
open class CustomerRepositoryImpl(
        @field:PersistenceContext val entityManager: EntityManager
) : CustomerRepository {
    
    @ReadOnly
    override fun findById(id: Long): Customer? = entityManager.find(Customer::class.java, id)

    @Transactional
    override fun save(customer: Customer) = entityManager.persist(customer)
}
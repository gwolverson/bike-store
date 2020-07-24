package order.service.repository

import io.micronaut.transaction.annotation.ReadOnly
import order.service.domain.Address
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Singleton
open class AddressRepositoryImpl(
        @field:PersistenceContext val entityManager: EntityManager
) : AddressRepository {
    
    @ReadOnly
    override fun findById(id: Long): Address? = entityManager.find(Address::class.java, id)

    @Transactional
    override fun save(address: Address) = entityManager.persist(address)
}
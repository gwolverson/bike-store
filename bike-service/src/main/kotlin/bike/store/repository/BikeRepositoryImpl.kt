package bike.store.repository

import bike.store.domain.Bike
import bike.store.model.Category
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Singleton
open class BikeRepositoryImpl(
        @param:CurrentSession @field:PersistenceContext val entityManager: EntityManager
) : BikeRepository {

    @Transactional(readOnly = true)
    override fun findById(id: Long): Bike? = entityManager.find(Bike::class.java, id)

    @Transactional(readOnly = true)
    override fun findAll(): List<Bike> =
            entityManager.createQuery("SELECT b FROM bike b", Bike::class.java)
                    .resultList as List<Bike>

    @Transactional(readOnly = true)
    override fun findAllByCategory(category: Category): List<Bike> =
            entityManager.createQuery("SELECT b FROM bike b WHERE b.category = :category", Bike::class.java)
                    .setParameter("category", category)
                    .resultList as List<Bike>

    @Transactional
    override fun save(bike: Bike) = entityManager.persist(bike)

    @Transactional
    override fun deleteById(id: Long) {
        val bikeToRemove = findById(id)
        entityManager.remove(bikeToRemove)
    }
}
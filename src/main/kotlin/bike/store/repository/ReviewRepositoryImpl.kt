package bike.store.repository

import bike.store.domain.Review
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Singleton
open class ReviewRepositoryImpl(
        @param:CurrentSession @field:PersistenceContext val entityManager: EntityManager
) : ReviewRepository {

    @Transactional(readOnly = true)
    override fun findById(id: Long): Review? = entityManager.find(Review::class.java, id)

    @Transactional(readOnly = true)
    override fun findAll(): List<Review> =
            entityManager.createQuery("SELECT r FROM review r", Review::class.java)
                    .resultList as List<Review>

    @Transactional
    override fun save(review: Review) = entityManager.persist(review)
}
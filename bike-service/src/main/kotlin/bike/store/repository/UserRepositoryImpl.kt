package bike.store.repository

import bike.store.domain.User
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Singleton
open class UserRepositoryImpl(
        @param:CurrentSession @field:PersistenceContext val entityManager: EntityManager
) : UserRepository {

    @Transactional
    override fun save(user: User) = entityManager.persist(user)

    @Transactional(readOnly = true)
    override fun findById(id: Long): User? = entityManager.find(User::class.java, id)
}
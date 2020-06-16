package bike.store.helpers

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Singleton
open class TransactionalTestHelper(
        @param:CurrentSession @field:PersistenceContext val entityManager: EntityManager
) {
    
    @Transactional
    open fun clearBike() = entityManager.createNativeQuery("DELETE FROM bike").executeUpdate()

    @Transactional
    open fun clearReview() = entityManager.createNativeQuery("DELETE FROM review").executeUpdate()

    @Transactional
    open fun clearUser() = entityManager.createNativeQuery("DELETE FROM user").executeUpdate()
}
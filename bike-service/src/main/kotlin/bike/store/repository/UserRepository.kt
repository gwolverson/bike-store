package bike.store.repository

import bike.store.domain.User

interface UserRepository {
    
    fun save(user: User)
    
    fun findById(id: Long): User?
}
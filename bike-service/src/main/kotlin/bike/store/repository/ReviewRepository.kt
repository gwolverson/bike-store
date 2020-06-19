package bike.store.repository

import bike.store.domain.Review

interface ReviewRepository {
    
    fun findById(id: Long): Review?
    
    fun findAll(): List<Review>
    
    fun save(review: Review)
}
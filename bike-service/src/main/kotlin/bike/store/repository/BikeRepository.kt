package bike.store.repository

import bike.store.domain.Bike
import bike.store.model.Category

interface BikeRepository {

    fun findById(id: Long): Bike?

    fun findAll(): List<Bike>
    
    fun findAllByCategory(category: Category): List<Bike>

    fun save(bike: Bike)
    
    fun deleteById(id: Long)
}
package order.service.repository

import order.service.domain.Order

interface OrderRepository {

    fun findById(id: Long): Order?
    
    fun save(order: Order)
}
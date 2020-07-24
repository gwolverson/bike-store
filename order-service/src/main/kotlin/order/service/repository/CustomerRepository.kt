package order.service.repository

import order.service.model.Order

interface OrderRepository {

    fun save(order: Order)
}
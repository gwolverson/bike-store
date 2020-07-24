package order.service.repository

import order.service.domain.Customer

interface CustomerRepository {

    fun findById(id: Int): Customer?
    
    fun save(customer: Customer)
}
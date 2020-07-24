package order.service.repository

import order.service.domain.Customer

interface CustomerRepository {

    fun findById(id: Long): Customer?
    
    fun save(customer: Customer)
}
package order.service.repository

import order.service.domain.Address

interface AddressRepository {

    fun findById(id: Long): Address?

    fun save(address: Address)
}
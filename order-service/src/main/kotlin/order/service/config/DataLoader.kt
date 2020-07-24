package order.service.config

import io.micronaut.context.annotation.Requires
import io.micronaut.context.env.Environment
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceReadyEvent
import order.service.domain.Address
import order.service.domain.Customer
import order.service.repository.AddressRepository
import order.service.repository.CustomerRepository
import javax.inject.Singleton

@Singleton
@Requires(env = [Environment.DEVELOPMENT])
class DataLoader(
        private val customerRepository: CustomerRepository,
        private val addressRepository: AddressRepository
) : ApplicationEventListener<ServiceReadyEvent> {

    override fun onApplicationEvent(event: ServiceReadyEvent?) {
        val customer = Customer(name = "Joe Bloggs")
        
        val address = Address(addressLine1 = "1 Temp Street", townCity = "Temp Ville", postcode = "TP1 M11", customer = customer)

        customerRepository.save(customer)
        addressRepository.save(address)
    }
}
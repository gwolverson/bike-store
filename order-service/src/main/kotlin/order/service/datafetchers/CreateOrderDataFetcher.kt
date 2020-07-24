package order.service.datafetchers

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import order.service.domain.Order
import order.service.repository.CustomerRepository
import order.service.repository.OrderRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateOrderDataFetcher(
        @Inject private val orderRepository: OrderRepository,
        @Inject private val customerRepository: CustomerRepository
) : DataFetcher<Order> {

    override fun get(environment: DataFetchingEnvironment?): Order {
        val bikeId = environment!!.getArgument<Int>("bikeId")
        val amount = environment.getArgument<Double>("amount")
        val customerId = environment.getArgument<Int>("customerId")
        
        val customer = customerRepository.findById(customerId.toLong())

        val order = Order(bikeId = bikeId, amount = amount, customer = customer)
        
        orderRepository.save(order)

        return order
    }
}
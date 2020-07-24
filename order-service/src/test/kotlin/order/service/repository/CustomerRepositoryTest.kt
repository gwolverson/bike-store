package order.service.repository

import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.test.annotation.MicronautTest
import order.service.domain.Customer
import order.service.domain.Order
import javax.inject.Inject

@MicronautTest
class OrderRepositoryTest(
        @Inject private val orderRepository: OrderRepository,
        @Inject private val customerRepository: CustomerRepository
) : BehaviorSpec({

    given("no order exists on the db") {
        `when`("save is called") {
            val customer = Customer(name = "Joe Bloggs")
            customerRepository.save(customer)
            
            val order = Order(bikeId = 1, amount = 1500.00, customer = customer)
            orderRepository.save(order)
            
            then("the user is saved and exists on the database") {
                val result = orderRepository.findById(1)
                result shouldNotBe null
                result!!.id shouldBe 1
                result.bikeId shouldBe 1
                result.amount shouldBe 1500.00
                result.customer!!.name shouldBe "Joe Bloggs"
            }
        }
    }
})
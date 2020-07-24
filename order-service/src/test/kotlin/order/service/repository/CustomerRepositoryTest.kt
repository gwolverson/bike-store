package order.service.repository

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.test.annotation.MicronautTest
import order.service.domain.Address
import order.service.domain.Customer
import javax.inject.Inject

@MicronautTest
class CustomerRepositoryTest(
        @Inject private val customerRepository: CustomerRepository,
        @Inject private val addressRepository: AddressRepository
) : BehaviorSpec({

    given("no customer exists on the db") {
        `when`("save is called") {
            val customer = Customer(name = "Joe Bloggs")
            customerRepository.save(customer)

            then("the customer is saved and exists on the database") {
                val result = customerRepository.findById(1)
                result shouldNotBe null
                result!!.id shouldBe 1
                result.name shouldBe "Joe Bloggs"
            }
        }
    }
})